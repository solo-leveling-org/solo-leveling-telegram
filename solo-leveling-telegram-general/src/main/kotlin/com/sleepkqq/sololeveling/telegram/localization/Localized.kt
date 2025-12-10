package com.sleepkqq.sololeveling.telegram.localization

import com.sleepkqq.sololeveling.telegram.keyboard.Keyboard

interface Localized {

	val localizationCode: LocalizationCode

	val params: Map<String, Any>
		get() = emptyMap()

	val keyboard: Keyboard?
		get() = null
}