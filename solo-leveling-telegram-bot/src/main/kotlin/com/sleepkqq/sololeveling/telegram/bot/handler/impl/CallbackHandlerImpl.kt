package com.sleepkqq.sololeveling.telegram.bot.handler.impl

import com.sleepkqq.sololeveling.telegram.bot.callback.Callback
import com.sleepkqq.sololeveling.telegram.bot.handler.CallbackQueryHandler
import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.methods.botapimethods.BotApiMethod
import org.telegram.telegrambots.meta.api.objects.CallbackQuery

@Component
class CallbackHandlerImpl(
	callbacks: List<Callback>
) : CallbackQueryHandler {

	private val callbacksMap = callbacks.associateBy { it.action.action }

	override fun handle(callbackQuery: CallbackQuery): BotApiMethod<*>? =
		callbacksMap[callbackQuery.data]?.handle(callbackQuery)
}
