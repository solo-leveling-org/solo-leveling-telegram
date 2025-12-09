package com.sleepkqq.sololeveling.telegram.bot.state

import com.sleepkqq.sololeveling.telegram.model.entity.user.state.BotSessionState
import kotlin.reflect.KClass

interface StateProcessor<T : BotSessionState> {

	/**
	 * Возвращает класс состояния, которое обрабатывает этот процессор
	 */
	fun getStateClass(): KClass<T>

	/**
	 * Выполняет бизнес-логику для состояния
	 */
	fun process(userId: Long, userInput: String, state: T)
}
