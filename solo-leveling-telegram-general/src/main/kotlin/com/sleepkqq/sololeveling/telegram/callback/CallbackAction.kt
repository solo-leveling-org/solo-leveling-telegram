package com.sleepkqq.sololeveling.telegram.callback

import com.sleepkqq.sololeveling.telegram.localization.LocalizationCode

enum class CallbackAction(
	val action: String,
	val localizationCode: LocalizationCode
) {

	INTERRUPT_CONFIRM("interrupt_confirm", LocalizationCode.BUTTON_INTERRUPT_CONFIRM),
	INTERRUPT_CANCEL("interrupt_cancel", LocalizationCode.BUTTON_INTERRUPT_CANCEL),
}