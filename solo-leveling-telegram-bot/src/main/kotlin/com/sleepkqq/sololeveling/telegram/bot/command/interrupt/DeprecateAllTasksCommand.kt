package com.sleepkqq.sololeveling.telegram.bot.command.interrupt

import com.sleepkqq.sololeveling.telegram.bot.model.UserRole
import com.sleepkqq.sololeveling.telegram.bot.service.user.UserSessionService
import com.sleepkqq.sololeveling.telegram.localization.CommandDescriptionCode
import com.sleepkqq.sololeveling.telegram.model.entity.user.UserSession
import com.sleepkqq.sololeveling.telegram.model.entity.user.state.BotSessionState
import com.sleepkqq.sololeveling.telegram.model.entity.user.state.task.DeprecateAllTasksConfirmationState
import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.objects.message.Message

@Component
class DeprecateAllTasksCommand(
	override val userSessionService: UserSessionService,
) : InterruptCommand {

	override val command: String = "/deprecate_all_tasks"
	override val description: CommandDescriptionCode = CommandDescriptionCode.DEPRECATE_ALL_TASKS
	override val requiredRole: UserRole = UserRole.DEVELOPER

	override fun createState(message: Message, session: UserSession): BotSessionState? =
		DeprecateAllTasksConfirmationState()
}
