package com.sleepkqq.sololeveling.telegram.bot.config

import org.springframework.boot.autoconfigure.cache.RedisCacheManagerBuilderCustomizer
import org.springframework.cache.annotation.EnableCaching
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.cache.RedisCacheConfiguration
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.RedisSerializationContext
import java.time.Duration

@Configuration
@EnableCaching
class RedisConfig {

	@Bean
	fun redisCacheManagerBuilderCustomizer() = RedisCacheManagerBuilderCustomizer { builder ->
		val configMap = mapOf(
			"user-info" to RedisCacheConfiguration.defaultCacheConfig()
				.entryTtl(Duration.ofMinutes(30))
				.serializeValuesWith(
					RedisSerializationContext.SerializationPair.fromSerializer(
						GenericJackson2JsonRedisSerializer()
					)
				)
		)

		builder.withInitialCacheConfigurations(configMap)
	}
}
