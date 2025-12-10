package com.sleepkqq.sololeveling.telegram.bot.command

interface Command {

	val command: String

	val forList: Boolean

	val description: String
		get() = ""
}
