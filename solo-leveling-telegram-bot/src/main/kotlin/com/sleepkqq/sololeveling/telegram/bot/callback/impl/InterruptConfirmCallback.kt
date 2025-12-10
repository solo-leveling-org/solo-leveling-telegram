package com.sleepkqq.sololeveling.telegram.bot.callback.impl

import com.sleepkqq.sololeveling.telegram.bot.callback.Callback
import com.sleepkqq.sololeveling.telegram.bot.service.localization.I18nService
import com.sleepkqq.sololeveling.telegram.bot.service.user.UserSessionService
import com.sleepkqq.sololeveling.telegram.callback.CallbackAction
import com.sleepkqq.sololeveling.telegram.model.entity.user.Fetchers
import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.methods.botapimethods.BotApiMethod
import org.telegram.telegrambots.meta.api.objects.CallbackQuery

@Component
class InterruptConfirmCallback(
	private val userSessionService: UserSessionService,
	private val i18nService: I18nService
) : Callback {

	override val action: CallbackAction = CallbackAction.INTERRUPT_CONFIRM

	override fun handle(callbackQuery: CallbackQuery): BotApiMethod<*> {
		val chatId = callbackQuery.message.chatId
		val messageId = callbackQuery.message.messageId

		val pendingInterruptState = userSessionService.get(
			chatId,
			Fetchers.USER_SESSION_FETCHER.pendingInterruptState()
		)
			.pendingInterruptState()
			?: return i18nService.deleteMessage(chatId, messageId)

		userSessionService.confirmInterruptState(chatId)

		return i18nService.editMessageText(chatId, messageId, pendingInterruptState.onEnterLocalized())
	}
}
