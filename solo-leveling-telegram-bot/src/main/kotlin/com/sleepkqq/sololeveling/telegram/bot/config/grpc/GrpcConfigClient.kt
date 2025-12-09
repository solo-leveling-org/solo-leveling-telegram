package com.sleepkqq.sololeveling.telegram.bot.config.grpc

import com.sleepkqq.sololeveling.proto.config.DefaultGrpcClientConfig
import com.sleepkqq.sololeveling.proto.config.interceptor.UserClientInterceptor
import com.sleepkqq.sololeveling.proto.user.UserServiceGrpc
import io.grpc.ClientInterceptor
import io.grpc.ManagedChannel
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@EnableConfigurationProperties(GrpcPlayerServiceProperties::class)
class GrpcConfigClient(
	properties: GrpcPlayerServiceProperties
) : DefaultGrpcClientConfig(properties) {

	@Bean
	fun playerManagedChannel(): ManagedChannel = createManagedChannel()

	@Bean
	fun localeClientInterceptor(): ClientInterceptor = UserClientInterceptor()

	@Bean
	fun userServiceBlockingStub(): UserServiceGrpc.UserServiceBlockingStub =
		UserServiceGrpc.newBlockingStub(playerManagedChannel())
			.withInterceptors(localeClientInterceptor())
}
