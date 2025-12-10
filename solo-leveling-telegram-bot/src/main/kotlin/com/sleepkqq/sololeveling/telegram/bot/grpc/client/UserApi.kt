package com.sleepkqq.sololeveling.telegram.bot.grpc.client

import com.google.protobuf.Empty
import com.sleepkqq.sololeveling.proto.user.GetUserAdditionalInfoResponse
import com.sleepkqq.sololeveling.proto.user.UserServiceGrpc.UserServiceBlockingStub
import org.springframework.stereotype.Service

@Service
class UserApi(
	private val userStub: UserServiceBlockingStub
) {

	fun getUserAdditionalInfo(): GetUserAdditionalInfoResponse =
		userStub.getUserAdditionalInfo(Empty.newBuilder().build())
}
