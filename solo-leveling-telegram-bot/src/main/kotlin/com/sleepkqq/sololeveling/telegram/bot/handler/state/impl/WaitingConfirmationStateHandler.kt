package com.sleepkqq.sololeveling.telegram.bot.handler.state.impl

import com.sleepkqq.sololeveling.telegram.bot.handler.state.SessionStateHandler
import com.sleepkqq.sololeveling.telegram.model.entity.user.state.transfer.WaitingConfirmationState
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import kotlin.reflect.KClass

@Component
class WaitingConfirmationStateHandler : SessionStateHandler<WaitingConfirmationState> {

	private val log = LoggerFactory.getLogger(javaClass)

	override fun handle(state: WaitingConfirmationState) {
		log.info("Waiting confirmation state: $state")
	}

	override fun stateClass(): KClass<WaitingConfirmationState> = WaitingConfirmationState::class
}
