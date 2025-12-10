package com.sleepkqq.sololeveling.telegram.keyboard

import com.sleepkqq.sololeveling.telegram.callback.CallbackAction

enum class Keyboard(
	val actions: List<CallbackAction>
) {

	INTERRUPT_CONFIRMATION(
		listOf(
			CallbackAction.INTERRUPT_CONFIRM,
			CallbackAction.INTERRUPT_CANCEL
		)
	),
}
