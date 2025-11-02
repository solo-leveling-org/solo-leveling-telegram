package com.sleepkqq.sololeveling.telegram.bot.handler.impl

import com.sleepkqq.sololeveling.telegram.bot.handler.MessageHandler
import com.sleepkqq.sololeveling.telegram.bot.handler.state.SessionStateHandler
import com.sleepkqq.sololeveling.telegram.bot.service.TelegramUserSessionService
import com.sleepkqq.sololeveling.telegram.model.entity.user.Immutables
import com.sleepkqq.sololeveling.telegram.model.entity.user.state.BotSessionState
import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.methods.botapimethods.BotApiMethod
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.message.Message
import java.time.Instant

@Component
class TextHandler(
	private val telegramUserSessionService: TelegramUserSessionService,
	stateHandlers: List<SessionStateHandler<out BotSessionState>>
) : MessageHandler {

	private val stateHandlersMap: Map<Class<out BotSessionState>, SessionStateHandler<out BotSessionState>> =
		stateHandlers.associateBy { it.stateClass().java }

	override fun handle(message: Message): BotApiMethod<*>? {
		val session = telegramUserSessionService.find(message.chatId)
			?: return SendMessage(
				message.chatId.toString(),
				"Привет! Для начала работы бота введи /start"
			)

		val newState = session.state().nextState(message.text)

		telegramUserSessionService.update(
			Immutables.createTelegramUserSession(session) {
				it.setState(newState)
			},
			Instant.now()
		)

		return SendMessage(message.chatId.toString(), newState.message())
	}
}
