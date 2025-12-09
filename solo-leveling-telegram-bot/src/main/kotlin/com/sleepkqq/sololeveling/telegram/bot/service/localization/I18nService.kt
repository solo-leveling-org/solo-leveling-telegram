package com.sleepkqq.sololeveling.telegram.bot.service.localization

import com.sleepkqq.sololeveling.telegram.bot.extensions.SendMessage
import com.sleepkqq.sololeveling.telegram.localization.LocalizationCode
import org.springframework.context.MessageSource
import org.springframework.context.i18n.LocaleContextHolder
import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.methods.send.SendMessage

@Service
class I18nService(
	private val messageSource: MessageSource
) {

	private fun getMessageInternal(key: String, args: Array<out Any?>?): String {
		return messageSource.getMessage(key, args, LocaleContextHolder.getLocale())
	}

	fun getMessage(key: String, vararg args: Any?): String {
		return getMessageInternal(key, if (args.isEmpty()) null else args)
	}

	fun getMessage(key: LocalizationCode, vararg args: Any?): String {
		return getMessageInternal(key.path, if (args.isEmpty()) null else args)
	}

	fun getMessage(key: LocalizationCode, params: Map<String, Any>): String {
		return if (params.isEmpty()) {
			getMessage(key)
		} else {
			getMessageInternal(key.path, params.values.toTypedArray())
		}
	}

	fun getMessage(key: String, params: Map<String, Any>): String {
		return if (params.isEmpty()) {
			getMessage(key)
		} else {
			getMessageInternal(key, params.values.toTypedArray())
		}
	}

	fun sendMessage(chatId: Long, key: LocalizationCode, vararg args: Any?): SendMessage {
		return SendMessage(chatId, getMessage(key, *args))
	}

	fun sendMessage(chatId: Long, key: LocalizationCode, params: Map<String, Any>): SendMessage {
		return SendMessage(chatId, getMessage(key, params))
	}

	fun sendMessage(chatId: Long, key: String, vararg args: Any?): SendMessage {
		return SendMessage(chatId, getMessage(key, *args))
	}

	fun sendMessage(chatId: Long, key: String, params: Map<String, Any>): SendMessage {
		return SendMessage(chatId, getMessage(key, params))
	}
}
