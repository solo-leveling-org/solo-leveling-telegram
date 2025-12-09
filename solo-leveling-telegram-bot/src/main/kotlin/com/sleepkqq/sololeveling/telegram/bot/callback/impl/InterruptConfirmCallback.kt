package com.sleepkqq.sololeveling.telegram.bot.callback.impl

import com.sleepkqq.sololeveling.telegram.bot.callback.Callback
import com.sleepkqq.sololeveling.telegram.bot.service.localization.I18nService
import com.sleepkqq.sololeveling.telegram.bot.service.user.UserSessionService
import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.methods.botapimethods.BotApiMethod
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText
import org.telegram.telegrambots.meta.api.objects.CallbackQuery

@Component
class InterruptConfirmCallback(
	private val userSessionService: UserSessionService,
	private val i18nService: I18nService
) : Callback {

	override val action: String = "interrupt_confirm"

	override fun handle(callbackQuery: CallbackQuery): BotApiMethod<*> {
		val chatId = callbackQuery.message.chatId
		userSessionService.confirmInterruptState(chatId)
		val state = userSessionService.get(chatId).state()

		return EditMessageText.builder()
			.chatId(chatId.toString())
			.messageId(callbackQuery.message.messageId)
			.text(i18nService.getMessage(state.onEnterMessageCode(), state.onEnterMessageParams()))
			.build()
	}
}
