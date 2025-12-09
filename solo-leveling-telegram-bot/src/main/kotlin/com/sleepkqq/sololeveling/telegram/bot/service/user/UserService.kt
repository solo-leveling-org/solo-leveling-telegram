package com.sleepkqq.sololeveling.telegram.bot.service.user

import com.sleepkqq.sololeveling.telegram.model.entity.user.User

interface UserService {

	fun register(id: Long): User
}