package com.sleepkqq.sololeveling.telegram.bot.command.info

import com.sleepkqq.sololeveling.telegram.bot.command.Command
import com.sleepkqq.sololeveling.telegram.bot.command.info.InfoCommand.InfoCommandResult
import com.sleepkqq.sololeveling.telegram.bot.service.auth.AuthService
import com.sleepkqq.sololeveling.telegram.bot.service.localization.impl.I18nService
import com.sleepkqq.sololeveling.telegram.localization.CommandCode
import com.sleepkqq.sololeveling.telegram.localization.CommandDescriptionCode
import org.springframework.beans.factory.ObjectProvider
import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.objects.message.Message

@Component
class HelpCommand(
	private val commandProvider: ObjectProvider<Command>,
	private val i18nService: I18nService,
	private val authService: AuthService
) : InfoCommand {

	private val commands: List<Command> by lazy {
		commandProvider.sortedBy { it.command }
	}

	override val command: String = "/help"
	override val description: CommandDescriptionCode = CommandDescriptionCode.HELP

	override fun handle(message: Message): InfoCommandResult {
		val helpText = commands
			.filter { authService.hasRole(it.requiredRole) }
			.joinToString("\n") { "${it.command} - ${i18nService.getMessage(it.description)}" }

		return InfoCommandResult(CommandCode.HELP, listOf(helpText))
	}
}
