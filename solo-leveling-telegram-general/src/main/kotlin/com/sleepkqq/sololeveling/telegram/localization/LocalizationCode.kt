package com.sleepkqq.sololeveling.telegram.localization

enum class LocalizationCode(val path: String) {

	// ========== Commands ==========
	CMD_HELP("command.help"),
	CMD_START("command.start"),
	CMD_UNKNOWN("command.unknown"),
	CMD_INTERRUPT("command.interrupt"),

	// ========== States ==========
	STATE_IDLE("state.idle"),
	STATE_TRANSFER_AMOUNT("state.transfer.amount"),
	STATE_TRANSFER_RECIPIENT("state.transfer.recipient"),
	STATE_TRANSFER_CONFIRMATION("state.transfer.confirmation"),
	STATE_FEEDBACK_ENTER("state.feedback.enter"),
	STATE_FEEDBACK_EXIT("state.feedback.exit"),

	// ========== Callbacks ==========
	CALLBACK_BUTTON_PRESSED("callback.button.pressed"),

	// ========== Buttons ==========
	BUTTON_INTERRUPT_CONFIRM("button.interrupt.confirm"),
	BUTTON_INTERRUPT_CANCEL("button.interrupt.cancel")
}
