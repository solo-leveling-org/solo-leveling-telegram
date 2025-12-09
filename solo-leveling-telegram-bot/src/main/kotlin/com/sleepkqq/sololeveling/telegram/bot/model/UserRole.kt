package com.sleepkqq.sololeveling.telegram.bot.model

import org.springframework.security.core.GrantedAuthority

enum class UserRole : GrantedAuthority {
	USER,
	ADMIN;

	override fun getAuthority(): String = name
}