package com.sleepkqq.sololeveling.telegram.callback

import com.sleepkqq.sololeveling.telegram.localization.ButtonCode
import com.sleepkqq.sololeveling.telegram.localization.LocalizationCode

enum class CallbackAction(
	val action: String,
	val localizationCode: LocalizationCode
) {

	INTERRUPT_CONFIRM("interrupt_confirm", ButtonCode.CONFIRM),
	INTERRUPT_CANCEL("interrupt_cancel", ButtonCode.CANCEL),
	DEPRECATE_ALL_TASKS_CONFIRM("deprecate_all_tasks_confirm", ButtonCode.CONFIRM),
	DEPRECATE_TASKS_BY_TOPIC_CONFIRM("deprecate_tasks_by_topic_confirm", ButtonCode.CONFIRM),
	IDLE_CANCEL("idle_cancel", ButtonCode.CANCEL),
	RESET_PLAYER_CONFIRM("reset_player_confirm", ButtonCode.CONFIRM),
	SEND_NEWSLETTER_CONFIRM("send_newsletter_confirm", ButtonCode.CONFIRM)
}