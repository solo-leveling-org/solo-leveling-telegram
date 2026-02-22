package com.sleepkqq.sololeveling.telegram.bot.command.interrupt

import com.sleepkqq.sololeveling.telegram.bot.model.UserRole
import com.sleepkqq.sololeveling.telegram.bot.service.user.UserSessionService
import com.sleepkqq.sololeveling.telegram.localization.CommandDescriptionCode
import com.sleepkqq.sololeveling.telegram.model.entity.user.UserSession
import com.sleepkqq.sololeveling.telegram.model.entity.user.state.BotSessionState
import com.sleepkqq.sololeveling.telegram.model.entity.user.state.newsletter.NewsletterNameState
import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.objects.message.Message

@Component
class SendNewsletterCommand(
	override val userSessionService: UserSessionService
) : InterruptCommand {

	override val command: String = "/send_newsletter"
	override val description: CommandDescriptionCode = CommandDescriptionCode.SEND_NEWSLETTER
	override val requiredRole: UserRole = UserRole.ADMIN

	override fun createState(message: Message, session: UserSession): BotSessionState? =
		NewsletterNameState()
}
