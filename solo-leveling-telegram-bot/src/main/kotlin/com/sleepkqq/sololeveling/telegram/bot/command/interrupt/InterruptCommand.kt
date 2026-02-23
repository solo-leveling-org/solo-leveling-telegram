package com.sleepkqq.sololeveling.telegram.bot.command.interrupt

import com.sleepkqq.sololeveling.telegram.bot.command.Command
import com.sleepkqq.sololeveling.telegram.bot.service.user.UserSessionService
import com.sleepkqq.sololeveling.telegram.keyboard.Keyboard
import com.sleepkqq.sololeveling.telegram.localization.CommandCode
import com.sleepkqq.sololeveling.telegram.localization.Localized
import com.sleepkqq.sololeveling.telegram.model.entity.Immutables
import com.sleepkqq.sololeveling.telegram.model.entity.user.UserSession
import com.sleepkqq.sololeveling.telegram.model.entity.user.state.BotSessionState
import com.sleepkqq.sololeveling.telegram.model.entity.user.state.IdleState
import org.telegram.telegrambots.meta.api.objects.message.Message

interface InterruptCommand : Command {

	val userSessionService: UserSessionService

	fun createState(message: Message, session: UserSession): BotSessionState?

	fun handle(message: Message, session: UserSession): InterruptCommandResult? =
		if (session.state() is IdleState) {
			changeState(message, session)
		} else {
			pendingInterruptState(message, session)
		}

	private fun changeState(
		message: Message,
		session: UserSession
	): InterruptCommandResult.StateChanged? {
		val newState = createState(message, session) ?: return null

		userSessionService.update(
			Immutables.createUserSession(session) {
				it.setState(newState)
			}
		)

		return InterruptCommandResult.StateChanged(newState)
	}

	private fun pendingInterruptState(
		message: Message,
		session: UserSession
	): InterruptCommandResult.Question? {
		val pendingState = createState(message, session) ?: return null

		userSessionService.update(
			Immutables.createUserSession(session) {
				it.setPendingInterruptState(pendingState)
			}
		)

		return InterruptCommandResult.Question()
	}

	sealed class InterruptCommandResult {

		abstract val localized: Localized

		data class Question(
			override val localized: Localized = object : Localized {
				override val localizationCode = CommandCode.INTERRUPT
				override val keyboard = Keyboard.INTERRUPT_CONFIRMATION
			}
		) : InterruptCommandResult()

		data class StateChanged(
			private val botSessionState: BotSessionState
		) : InterruptCommandResult() {
			override val localized: Localized
				get() = botSessionState.onEnterLocalized()
		}
	}
}
