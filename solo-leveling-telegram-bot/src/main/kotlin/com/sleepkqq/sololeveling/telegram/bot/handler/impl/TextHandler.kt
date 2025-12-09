package com.sleepkqq.sololeveling.telegram.bot.handler.impl

import com.sleepkqq.sololeveling.telegram.bot.handler.MessageHandler
import com.sleepkqq.sololeveling.telegram.bot.service.localization.I18nService
import com.sleepkqq.sololeveling.telegram.bot.service.user.UserSessionService
import com.sleepkqq.sololeveling.telegram.bot.state.StateProcessor
import com.sleepkqq.sololeveling.telegram.localization.LocalizationCode
import com.sleepkqq.sololeveling.telegram.model.entity.user.Immutables
import com.sleepkqq.sololeveling.telegram.model.entity.user.state.BotSessionState
import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.methods.botapimethods.BotApiMethod
import org.telegram.telegrambots.meta.api.objects.message.Message
import kotlin.reflect.KClass

@Component
class TextHandler(
	private val userSessionService: UserSessionService,
	private val i18nService: I18nService,
	stateProcessors: List<StateProcessor<BotSessionState>>
) : MessageHandler {

	private val stateProcessorsMap: Map<KClass<BotSessionState>, StateProcessor<BotSessionState>> =
		stateProcessors.associateBy { it.getStateClass() }

	override fun handle(message: Message): BotApiMethod<*>? {
		val session = userSessionService.find(message.chatId)
			?: return i18nService.sendMessage(message.chatId, LocalizationCode.STATE_IDLE)

		val currentState = session.state()
		stateProcessorsMap[currentState::class]
			?.process(message.chatId, message.text, currentState)

		val newState = currentState.nextState(message.text)

		if (currentState != newState) {
			userSessionService.update(
				Immutables.createUserSession(session) {
					it.setState(newState)
						.setPendingInterruptState(null)
				}
			)
		}

		val onExitMessageCode = currentState.onExitMessageCode()
		if (onExitMessageCode != null) {
			return i18nService.sendMessage(
				message.chatId,
				onExitMessageCode,
				currentState.onExitMessageParams()
			)
		}

		return i18nService.sendMessage(
			message.chatId,
			newState.onEnterMessageCode(),
			newState.onEnterMessageParams()
		)
	}
}
