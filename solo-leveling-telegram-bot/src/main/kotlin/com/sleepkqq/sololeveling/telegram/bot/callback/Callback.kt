package com.sleepkqq.sololeveling.telegram.bot.callback

import org.telegram.telegrambots.meta.api.methods.botapimethods.BotApiMethod
import org.telegram.telegrambots.meta.api.objects.CallbackQuery

interface Callback {

	val action: String

	fun handle(callbackQuery: CallbackQuery): BotApiMethod<*>?
}
