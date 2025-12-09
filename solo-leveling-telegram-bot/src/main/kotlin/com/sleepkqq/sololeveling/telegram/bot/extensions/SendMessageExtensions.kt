package com.sleepkqq.sololeveling.telegram.bot.extensions

import org.telegram.telegrambots.meta.api.methods.send.SendMessage

fun SendMessage(chatId: Long, text: String): SendMessage {
	return SendMessage(chatId.toString(), text)
}
