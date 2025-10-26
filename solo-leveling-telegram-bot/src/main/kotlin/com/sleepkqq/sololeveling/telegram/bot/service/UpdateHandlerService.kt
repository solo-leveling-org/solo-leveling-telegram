package com.sleepkqq.sololeveling.telegram.bot.service

import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.methods.botapimethods.BotApiMethod
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update

@Service
class UpdateHandlerService {

	fun handleUpdate(update: Update): BotApiMethod<*>? {
		if (update.hasMessage() && update.message.hasText()) {
			val chatId = update.message.chatId.toString()
			val text = update.message.text
			val replyText = when {
				text.equals("/start", ignoreCase = true) -> "Привет! 👋 Я твой Spring Boot бот."
				text.startsWith("/echo ") -> text.removePrefix("/echo ")
				else -> "Ты написал: $text"
			}

			return SendMessage(chatId, replyText)
		}

		return null
	}
}
