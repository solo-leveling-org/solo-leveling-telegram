package com.sleepkqq.sololeveling.telegram.bot.event

import com.sleepkqq.sololeveling.telegram.model.entity.broadcast.dto.ScheduledBroadcastView

data class RunBroadcastEvent(
	val broadcast: ScheduledBroadcastView
)
