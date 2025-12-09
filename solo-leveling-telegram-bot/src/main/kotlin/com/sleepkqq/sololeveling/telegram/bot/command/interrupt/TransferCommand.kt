package com.sleepkqq.sololeveling.telegram.bot.command.interrupt

import com.sleepkqq.sololeveling.telegram.bot.command.InterruptCommand
import com.sleepkqq.sololeveling.telegram.bot.service.session.TelegramUserSessionService
import com.sleepkqq.sololeveling.telegram.localization.LocalizationCode
import com.sleepkqq.sololeveling.telegram.model.entity.user.Immutables
import com.sleepkqq.sololeveling.telegram.model.entity.user.TelegramUserSession
import com.sleepkqq.sololeveling.telegram.model.entity.user.state.transfer.TransferAmountState
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.objects.message.Message

@Profile("player")
@Component
class TransferCommand(
	private val telegramUserSessionService: TelegramUserSessionService
) : InterruptCommand() {

	override val command: String = "/transfer"

	override fun changeState(
		message: Message,
		session: TelegramUserSession
	): InterruptCommandResult.StateChanged {

		telegramUserSessionService.update(
			Immutables.createTelegramUserSession(session) {
				it.setState(TransferAmountState())
			}
		)

		return InterruptCommandResult.StateChanged(LocalizationCode.CMD_TRANSFER)
	}

	override fun pendingInterruptState(message: Message, session: TelegramUserSession) {
		telegramUserSessionService.update(
			Immutables.createTelegramUserSession(session) {
				it.setPendingInterruptState(TransferAmountState())
			}
		)
	}
}
