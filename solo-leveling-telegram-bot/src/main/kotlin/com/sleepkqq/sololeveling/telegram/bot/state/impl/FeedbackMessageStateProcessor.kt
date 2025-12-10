package com.sleepkqq.sololeveling.telegram.bot.state.impl

import com.sleepkqq.sololeveling.telegram.bot.service.user.UserFeedbackService
import com.sleepkqq.sololeveling.telegram.bot.state.StateProcessor
import com.sleepkqq.sololeveling.telegram.model.entity.user.state.feedback.FeedbackMessageState
import org.springframework.stereotype.Component
import kotlin.reflect.KClass

@Component
class FeedbackMessageStateProcessor(
	private val userFeedbackService: UserFeedbackService
) : StateProcessor<FeedbackMessageState> {

	override fun getStateClass(): KClass<FeedbackMessageState> = FeedbackMessageState::class

	override fun process(userId: Long, userInput: String, state: FeedbackMessageState) {
		userFeedbackService.create(userId, userInput)
	}
}