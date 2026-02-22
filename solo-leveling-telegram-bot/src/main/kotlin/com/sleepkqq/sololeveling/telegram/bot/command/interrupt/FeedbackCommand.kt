package com.sleepkqq.sololeveling.telegram.bot.command.interrupt

import com.sleepkqq.sololeveling.telegram.bot.service.user.UserSessionService
import com.sleepkqq.sololeveling.telegram.localization.CommandDescriptionCode
import com.sleepkqq.sololeveling.telegram.model.entity.user.UserSession
import com.sleepkqq.sololeveling.telegram.model.entity.user.state.BotSessionState
import com.sleepkqq.sololeveling.telegram.model.entity.user.state.feedback.FeedbackMessageState
import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.objects.message.Message

@Component
class FeedbackCommand(
	override val userSessionService: UserSessionService
) : InterruptCommand {

	override val command: String = "/feedback"
	override val description: CommandDescriptionCode = CommandDescriptionCode.FEEDBACK

	override fun createState(message: Message, session: UserSession): BotSessionState? =
		FeedbackMessageState()
}
