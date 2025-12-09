package com.sleepkqq.sololeveling.telegram.bot.handler.impl

import com.sleepkqq.sololeveling.telegram.bot.handler.MessageHandler
import com.sleepkqq.sololeveling.telegram.bot.service.localization.I18nService
import com.sleepkqq.sololeveling.telegram.bot.service.session.TelegramUserSessionService
import com.sleepkqq.sololeveling.telegram.localization.LocalizationCode
import com.sleepkqq.sololeveling.telegram.model.entity.user.Immutables
import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.methods.botapimethods.BotApiMethod
import org.telegram.telegrambots.meta.api.objects.message.Message

@Component
class TextHandler(
	private val telegramUserSessionService: TelegramUserSessionService,
	private val i18nService: I18nService
) : MessageHandler {

	override fun handle(message: Message): BotApiMethod<*>? {
		val session = telegramUserSessionService.find(message.chatId)
			?: return i18nService.sendMessage(message.chatId, LocalizationCode.STATE_IDLE)

		val currentState = session.state()
		val newState = currentState.nextState(message.text)

		if (currentState != newState) {
			telegramUserSessionService.update(
				Immutables.createTelegramUserSession(session) {
					it.setState(newState)
				}
			)
		}

		return i18nService.sendMessage(
			message.chatId,
			newState.messageCode(),
			newState.messageParams()
		)
	}
}
