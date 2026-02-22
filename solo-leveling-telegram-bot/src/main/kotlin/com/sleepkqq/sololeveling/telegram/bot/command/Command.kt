package com.sleepkqq.sololeveling.telegram.bot.command

import com.sleepkqq.sololeveling.telegram.bot.model.UserRole
import com.sleepkqq.sololeveling.telegram.localization.CommandDescriptionCode

interface Command {

	val command: String

	val description: CommandDescriptionCode

	val requiredRole: UserRole
		get() = UserRole.USER
}
