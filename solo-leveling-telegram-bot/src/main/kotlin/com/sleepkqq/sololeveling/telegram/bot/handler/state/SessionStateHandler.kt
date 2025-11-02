package com.sleepkqq.sololeveling.telegram.bot.handler.state

import com.sleepkqq.sololeveling.telegram.model.entity.user.state.BotSessionState
import kotlin.reflect.KClass

interface SessionStateHandler<S : BotSessionState> {

	fun handle(state: S)

	fun stateClass(): KClass<S>
}
