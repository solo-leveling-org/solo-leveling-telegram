package com.sleepkqq.sololeveling.telegram.bot.command.interrupt

import com.sleepkqq.sololeveling.telegram.bot.command.interrupt.InterruptCommand.InterruptCommandResult.StateChanged
import com.sleepkqq.sololeveling.telegram.bot.service.user.UserSessionService
import com.sleepkqq.sololeveling.telegram.model.entity.user.Immutables
import com.sleepkqq.sololeveling.telegram.model.entity.user.UserSession
import com.sleepkqq.sololeveling.telegram.model.entity.user.state.feedback.FeedbackMessageState
import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.objects.message.Message

@Component
class FeedbackCommand(
	private val userSessionService: UserSessionService
) : InterruptCommand {

	override val command: String = "/feedback"

	override fun changeState(message: Message, session: UserSession): StateChanged {
		val newState = FeedbackMessageState()
		userSessionService.update(
			Immutables.createUserSession(session) {
				it.setState(newState)
			}
		)

		return StateChanged(newState)
	}

	override fun pendingInterruptState(message: Message, session: UserSession) {
		userSessionService.update(
			Immutables.createUserSession(session) {
				it.setPendingInterruptState(FeedbackMessageState())
			}
		)
	}
}
