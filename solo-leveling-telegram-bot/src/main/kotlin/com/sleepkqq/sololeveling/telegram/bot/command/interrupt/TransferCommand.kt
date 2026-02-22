package com.sleepkqq.sololeveling.telegram.bot.command.interrupt

import com.sleepkqq.sololeveling.telegram.bot.service.user.UserSessionService
import com.sleepkqq.sololeveling.telegram.localization.CommandDescriptionCode
import com.sleepkqq.sololeveling.telegram.model.entity.user.UserSession
import com.sleepkqq.sololeveling.telegram.model.entity.user.state.BotSessionState
import com.sleepkqq.sololeveling.telegram.model.entity.user.state.transfer.TransferAmountState
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.objects.message.Message

@Profile("transfer")
@Component
class TransferCommand(
	override val userSessionService: UserSessionService
) : InterruptCommand {

	override val command: String = "/transfer"
	override val description: CommandDescriptionCode = CommandDescriptionCode.TRANSFER

	override fun createState(message: Message, session: UserSession): BotSessionState? =
		TransferAmountState()
}
