package com.sleepkqq.sololeveling.telegram.localization

enum class LocalizationCode(val path: String) {

	// ========== Commands ==========
	CMD_HELP("command.help"),
	CMD_START("command.start"),
	CMD_PROFILE("command.profile"),
	CMD_TRANSFER("command.transfer"),
	CMD_STATS("command.stats"),
	CMD_UNKNOWN("command.unknown"),
	CMD_INTERRUPT("command.interrupt"),

	// ========== States ==========
	STATE_IDLE("state.idle"),
	STATE_TRANSFER_AMOUNT("state.transfer.amount"),
	STATE_TRANSFER_RECIPIENT("state.transfer.recipient"),
	STATE_TRANSFER_CONFIRMATION("state.transfer.confirmation"),

	// ========== Callbacks ==========
	CALLBACK_BUTTON_PRESSED("callback.button.pressed")
}
