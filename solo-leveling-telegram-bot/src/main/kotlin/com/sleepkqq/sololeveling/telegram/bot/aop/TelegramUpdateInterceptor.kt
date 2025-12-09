package com.sleepkqq.sololeveling.telegram.bot.aop

import com.sleepkqq.sololeveling.config.interceptor.UserContextHolder
import com.sleepkqq.sololeveling.telegram.bot.service.auth.AuthService
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.slf4j.LoggerFactory
import org.springframework.context.i18n.LocaleContextHolder
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.objects.Update

@Aspect
@Component
class TelegramUpdateInterceptor(
	private val authService: AuthService
) {

	private val log = LoggerFactory.getLogger(javaClass)

	@Around("execution(* com.sleepkqq.sololeveling.telegram.bot.dispatcher.UpdateDispatcher.dispatch(..)) && args(update)")
	fun interceptUpdateDispatch(pjp: ProceedingJoinPoint, update: Update): Any? {
		try {
			authService.login(update)
			return pjp.proceed()

		} catch (ex: Exception) {
			log.error("Error processing telegram update", ex)
			throw ex

		} finally {
			clearContexts()
		}
	}

	private fun clearContexts() {
		try {
			SecurityContextHolder.clearContext()
			LocaleContextHolder.resetLocaleContext()
			UserContextHolder.clear()

		} catch (ex: Exception) {
			log.warn("Error clearing thread-local contexts", ex)
		}
	}
}
