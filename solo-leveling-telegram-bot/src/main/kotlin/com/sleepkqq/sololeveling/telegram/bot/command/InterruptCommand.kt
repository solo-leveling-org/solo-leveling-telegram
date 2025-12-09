package com.sleepkqq.sololeveling.telegram.bot.command

import com.sleepkqq.sololeveling.telegram.localization.LocalizationCode
import com.sleepkqq.sololeveling.telegram.model.entity.user.TelegramUserSession
import com.sleepkqq.sololeveling.telegram.model.entity.user.state.IdleState
import org.telegram.telegrambots.meta.api.objects.message.Message

abstract class InterruptCommand : Command {

	fun handle(message: Message, session: TelegramUserSession): InterruptCommandResult =
		if (session.state() is IdleState) {
			changeState(message, session)
		} else {
			pendingInterruptState(message, session)
			InterruptCommandResult.Question()
		}

	abstract fun changeState(
		message: Message,
		session: TelegramUserSession
	): InterruptCommandResult.StateChanged

	abstract fun pendingInterruptState(message: Message, session: TelegramUserSession)

	sealed class InterruptCommandResult {

		data class Question(
			val localizationCode: LocalizationCode = LocalizationCode.CMD_INTERRUPT
		) : InterruptCommandResult()

		data class StateChanged(
			val localizationCode: LocalizationCode,
			val params: Map<String, Any> = emptyMap()
		) : InterruptCommandResult()
	}
}
