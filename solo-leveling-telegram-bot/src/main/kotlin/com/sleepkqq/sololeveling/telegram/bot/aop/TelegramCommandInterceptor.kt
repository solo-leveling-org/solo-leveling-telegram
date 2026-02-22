package com.sleepkqq.sololeveling.telegram.bot.aop

import com.sleepkqq.sololeveling.config.interceptor.UserContextHolder
import com.sleepkqq.sololeveling.telegram.bot.command.Command
import com.sleepkqq.sololeveling.telegram.bot.model.UserRole
import com.sleepkqq.sololeveling.telegram.bot.service.auth.AuthService
import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.slf4j.LoggerFactory
import org.springframework.core.annotation.Order
import org.springframework.security.authorization.AuthorizationDeniedException
import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.objects.message.Message

@Aspect
@Order(20)
@Component
class TelegramCommandInterceptor(
	private val authService: AuthService,
	commands: List<Command>
) {

	private val log = LoggerFactory.getLogger(javaClass)
	private val commandsMap: Map<String, Command> = commands.associateBy { it.command }

	@Before("execution(* com.sleepkqq.sololeveling.telegram.bot.handler.impl.CommandHandler.handle(..))")
	fun checkAccess(joinPoint: JoinPoint) {
		val message = joinPoint.args.firstOrNull() as? Message ?: return
		val command = commandsMap[message.text.split(" ").first()] ?: return
		val userId = UserContextHolder.getUserId()!!

		log.info("Command={} called by userId={}", command.command, userId)

		if (command.requiredRole == UserRole.USER) return

		if (!authService.hasRole(command.requiredRole)) {
			throw AuthorizationDeniedException(
				"Access denied for userId=$userId, role=${command.requiredRole} required"
			)
		}
	}
}
