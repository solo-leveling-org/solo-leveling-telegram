package com.sleepkqq.sololeveling.telegram.bot.config.retry

import com.sleepkqq.sololeveling.telegram.bot.config.properties.TelegramRetryProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.retry.support.RetryTemplate

@Configuration
class RetryConfig(
	private val telegramRetryProperties: TelegramRetryProperties
) {

	@Bean
	fun telegramRetryTemplate(): RetryTemplate = RetryTemplate.builder()
		.exponentialBackoff(
			telegramRetryProperties.initialInterval,
			telegramRetryProperties.multiplier,
			telegramRetryProperties.maxInterval,
			true
		)
		.customPolicy(TelegramRetryPolicy(telegramRetryProperties.maxAttempts))
		.build()
}
