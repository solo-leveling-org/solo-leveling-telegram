package com.sleepkqq.sololeveling.telegram.bot.command.interrupt

import com.sleepkqq.sololeveling.telegram.bot.command.Command
import com.sleepkqq.sololeveling.telegram.keyboard.Keyboard
import com.sleepkqq.sololeveling.telegram.localization.LocalizationCode
import com.sleepkqq.sololeveling.telegram.localization.Localized
import com.sleepkqq.sololeveling.telegram.model.entity.user.UserSession
import com.sleepkqq.sololeveling.telegram.model.entity.user.state.BotSessionState
import com.sleepkqq.sololeveling.telegram.model.entity.user.state.IdleState
import org.telegram.telegrambots.meta.api.objects.message.Message

interface InterruptCommand : Command {

	fun handle(message: Message, session: UserSession): InterruptCommandResult =
		if (session.state() is IdleState) {
			changeState(message, session)
		} else {
			pendingInterruptState(message, session)
			InterruptCommandResult.Question()
		}

	fun changeState(
		message: Message,
		session: UserSession
	): InterruptCommandResult.StateChanged

	fun pendingInterruptState(message: Message, session: UserSession)

	sealed class InterruptCommandResult : Localized {

		data class Question(
			override val localizationCode: LocalizationCode = LocalizationCode.CMD_INTERRUPT,
			override val keyboard: Keyboard = Keyboard.INTERRUPT_CONFIRMATION
		) : InterruptCommandResult()

		data class StateChanged(
			private val botSessionState: BotSessionState
		) : InterruptCommandResult() {

			override val localizationCode: LocalizationCode
				get() = botSessionState.onEnterMessageCode()

			override val params: Map<String, Any>
				get() = botSessionState.onEnterMessageParams()
		}
	}
}