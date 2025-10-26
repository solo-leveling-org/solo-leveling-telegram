package com.sleepkqq.sololeveling.telegram.bot.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestTemplate

@Configuration
class RestConfig {

	@Bean
	fun restTemplate(): RestTemplate = RestTemplate()
}
