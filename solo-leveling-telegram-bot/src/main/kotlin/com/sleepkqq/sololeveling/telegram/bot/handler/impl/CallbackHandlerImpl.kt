package com.sleepkqq.sololeveling.telegram.bot.handler.impl

import com.sleepkqq.sololeveling.telegram.bot.callback.Callback
import com.sleepkqq.sololeveling.telegram.bot.handler.CallbackQueryHandler
import com.sleepkqq.sololeveling.telegram.bot.service.localization.I18nService
import com.sleepkqq.sololeveling.telegram.localization.LocalizationCode
import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.methods.botapimethods.BotApiMethod
import org.telegram.telegrambots.meta.api.objects.CallbackQuery

@Component
class CallbackHandlerImpl(
	callbacks: List<Callback>,
	private val i18nService: I18nService
) : CallbackQueryHandler {

	private val callbacksMap = callbacks.associateBy { it.action }

	override fun handle(callbackQuery: CallbackQuery): BotApiMethod<*>? {
		val chatId = callbackQuery.message.chatId
		val callback = callbacksMap[callbackQuery.data]
			?: return i18nService.sendMessage(
				chatId,
				LocalizationCode.CALLBACK_BUTTON_PRESSED,
				"action" to callbackQuery.data
			)

		return callback.handle(callbackQuery)
	}
}
