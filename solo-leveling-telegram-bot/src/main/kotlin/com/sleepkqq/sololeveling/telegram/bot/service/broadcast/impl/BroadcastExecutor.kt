package com.sleepkqq.sololeveling.telegram.bot.service.broadcast.impl

import com.sleepkqq.sololeveling.proto.user.LocaleUserView
import com.sleepkqq.sololeveling.telegram.bot.extensions.SendMessage
import com.sleepkqq.sololeveling.telegram.bot.service.message.TelegramMessageSender
import com.sleepkqq.sololeveling.telegram.model.entity.broadcast.dto.ScheduledBroadcastView
import com.sleepkqq.sololeveling.telegram.model.entity.localziation.LocalizedMessage
import com.sleepkqq.sololeveling.telegram.model.entity.localziation.enums.MessageLocale
import kotlinx.coroutines.*
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto
import org.telegram.telegrambots.meta.api.objects.InputFile

@Component
class BroadcastExecutor(
	private val telegramMessageSender: TelegramMessageSender
) {

	private val log = LoggerFactory.getLogger(javaClass)

	fun execute(broadcast: ScheduledBroadcastView, users: List<LocaleUserView>): ExecutionResult {
		val messagesMap = broadcast.messages
			.associateBy { it.locale }
			.mapValues { it.value.toEntity() }

		var success = 0
		var failed = 0

		runBlocking {
			users.map { async(Dispatchers.IO) { sendTo(it, messagesMap, broadcast.fileId) } }
				.awaitAll().forEach { sent -> if (sent) success++ else failed++ }
		}

		return ExecutionResult(users.size, success, failed)
	}

	private fun sendTo(
		user: LocaleUserView,
		messagesMap: Map<MessageLocale, LocalizedMessage>,
		fileId: String?
	): Boolean = try {
		val locale = MessageLocale.valueOf(user.locale.uppercase())
		val message = messagesMap[locale]
			?: messagesMap[MessageLocale.EN]
			?: messagesMap.values.first()

		if (fileId != null) {
			telegramMessageSender.send(
				SendPhoto.builder()
					.chatId(user.id)
					.photo(InputFile(fileId))
					.caption(message.text())
					.build()
			)
		} else {
			telegramMessageSender.send(SendMessage(user.id, message.text()))
		}

		true

	} catch (e: Exception) {
		log.warn("Failed to send message to user ${user.id}", e)
		false
	}

	data class ExecutionResult(
		val total: Int,
		val totalSuccess: Int,
		val totalFailed: Int
	)
}
