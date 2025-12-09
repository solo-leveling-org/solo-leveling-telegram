package com.sleepkqq.sololeveling.telegram.bot.keyboard

import com.sleepkqq.sololeveling.telegram.bot.callback.Callback
import com.sleepkqq.sololeveling.telegram.bot.callback.impl.InterruptCancelCallback
import com.sleepkqq.sololeveling.telegram.bot.callback.impl.InterruptConfirmCallback
import com.sleepkqq.sololeveling.telegram.bot.service.localization.I18nService
import com.sleepkqq.sololeveling.telegram.localization.LocalizationCode
import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardRow

@Component
class KeyboardFactory(
	private val interruptConfirmCallback: InterruptConfirmCallback,
	private val interruptCancelCallback: InterruptCancelCallback,
	private val i18nService: I18nService
) {

	fun interruptConfirmationKeyboard(): InlineKeyboardMarkup {
		val confirmButton = createButton(
			LocalizationCode.BUTTON_INTERRUPT_CONFIRM,
			interruptConfirmCallback
		)
		val cancelButton = createButton(
			LocalizationCode.BUTTON_INTERRUPT_CANCEL,
			interruptCancelCallback
		)

		return InlineKeyboardMarkup(
			listOf(
				InlineKeyboardRow(listOf(confirmButton)),
				InlineKeyboardRow(listOf(cancelButton))
			)
		)
	}

	private fun createButton(textCode: LocalizationCode, callback: Callback): InlineKeyboardButton {
		return InlineKeyboardButton.builder()
			.text(i18nService.getMessage(textCode))
			.callbackData(callback.action)
			.build()
	}
}
