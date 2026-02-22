package com.sleepkqq.sololeveling.telegram.bot.command.info

import com.sleepkqq.sololeveling.telegram.bot.command.info.InfoCommand.InfoCommandResult
import com.sleepkqq.sololeveling.telegram.keyboard.Keyboard
import com.sleepkqq.sololeveling.telegram.localization.CommandCode
import com.sleepkqq.sololeveling.telegram.localization.CommandDescriptionCode
import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.objects.message.Message

@Component
class StartCommand : InfoCommand {

	override val command: String = "/start"
	override val description: CommandDescriptionCode = CommandDescriptionCode.START

	override fun handle(message: Message): InfoCommandResult =
		InfoCommandResult(CommandCode.START, keyboard = Keyboard.MINI_APP_LINK)
}
