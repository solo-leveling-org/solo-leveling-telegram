package com.sleepkqq.sololeveling.telegram.bot.config.properties

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("app.telegram.rate-limit")
data class TelegramRateLimitProperties(
	val sendMessage: SendMessageRateLimit
) {

	data class SendMessageRateLimit(
		val global: Long,
		val perChat: Long,
		val periodSeconds: Long
	)
}
