package com.sleepkqq.sololeveling.telegram.bot.callback.impl

import com.sleepkqq.sololeveling.telegram.bot.callback.Callback
import com.sleepkqq.sololeveling.telegram.bot.service.user.UserSessionService
import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.methods.botapimethods.BotApiMethod
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage
import org.telegram.telegrambots.meta.api.objects.CallbackQuery

@Component
class InterruptCancelCallback(
	private val userSessionService: UserSessionService
) : Callback {

	override val action: String = "interrupt_cancel"

	override fun handle(callbackQuery: CallbackQuery): BotApiMethod<*> {
		val chatId = callbackQuery.message.chatId

		userSessionService.cancelInterruptState(chatId)

		return DeleteMessage.builder()
			.chatId(chatId.toString())
			.messageId(callbackQuery.message.messageId)
			.build()
	}
}
