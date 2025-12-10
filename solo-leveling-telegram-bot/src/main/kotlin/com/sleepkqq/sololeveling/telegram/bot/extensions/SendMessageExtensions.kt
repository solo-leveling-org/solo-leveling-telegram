package com.sleepkqq.sololeveling.telegram.bot.extensions

import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup

fun SendMessage(chatId: Long, text: String): SendMessage {
	return SendMessage(chatId.toString(), text)
}

fun SendMessage.withReplyMarkup(replyMarkup: InlineKeyboardMarkup): SendMessage {
	return this.apply { this.replyMarkup = replyMarkup }
}

