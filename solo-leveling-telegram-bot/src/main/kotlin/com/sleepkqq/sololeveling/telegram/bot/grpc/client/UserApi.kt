package com.sleepkqq.sololeveling.telegram.bot.grpc.client

import com.google.protobuf.Empty
import com.sleepkqq.sololeveling.proto.user.GetUserAdditionalInfoResponse
import com.sleepkqq.sololeveling.proto.user.UserServiceGrpc.UserServiceBlockingStub
import io.grpc.StatusRuntimeException
import org.springframework.retry.annotation.Retryable
import org.springframework.stereotype.Service

@Service
class UserApi(
	private val userStub: UserServiceBlockingStub
) {

	@Retryable(retryFor = [StatusRuntimeException::class])
	fun getUserAdditionalInfo(): GetUserAdditionalInfoResponse =
		userStub.getUserAdditionalInfo(Empty.newBuilder().build())
}
