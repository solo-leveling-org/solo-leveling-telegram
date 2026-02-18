package com.sleepkqq.sololeveling.telegram.bot.service.broadcast.impl

import com.sleepkqq.sololeveling.proto.player.RequestPaging
import com.sleepkqq.sololeveling.telegram.bot.event.RunBroadcastEvent
import com.sleepkqq.sololeveling.telegram.bot.grpc.client.UserApi
import com.sleepkqq.sololeveling.telegram.bot.service.broadcast.ScheduledBroadcastService
import com.sleepkqq.sololeveling.telegram.model.entity.Immutables
import com.sleepkqq.sololeveling.telegram.model.entity.broadcast.enums.BroadcastStatus
import org.slf4j.LoggerFactory
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
class BroadcastRunner(
	private val broadcastExecutor: BroadcastExecutor,
	private val scheduledBroadcastService: ScheduledBroadcastService,
	private val userApi: UserApi
) {

	private companion object {
		const val PAGE_SIZE = 100
	}

	private val log = LoggerFactory.getLogger(javaClass)

	@EventListener
	fun listen(event: RunBroadcastEvent) {
		val broadcast = event.broadcast
		if (broadcast.status != BroadcastStatus.PENDING) return

		val inProgressBroadcast = scheduledBroadcastService.updateStatus(
			broadcast.toEntity(),
			BroadcastStatus.IN_PROGRESS
		)

		try {
			var currentPage = 0
			var total = 0
			var totalSuccess = 0
			var totalFailed = 0

			do {
				val paging = RequestPaging.newBuilder().setPage(currentPage).setPageSize(PAGE_SIZE).build()
				val response = userApi.getUsers(paging)

				val result = broadcastExecutor.execute(broadcast, response.usersList)
				total += result.total
				totalSuccess += result.totalSuccess
				totalFailed += result.totalFailed

				currentPage++
			} while (response.paging.hasMore)

			scheduledBroadcastService.update(Immutables.createScheduledBroadcast(inProgressBroadcast) {
				it.setStatus(BroadcastStatus.COMPLETED)
					.setTotal(total)
					.setTotalSuccess(totalSuccess)
					.setTotalFailed(totalFailed)
			})

			log.info(
				"Broadcast {} completed: total={} success={}, failed={}",
				broadcast.id, total, totalSuccess, totalFailed
			)

		} catch (e: Exception) {
			log.error("Broadcast {} failed", broadcast.id, e)
			scheduledBroadcastService.updateStatus(inProgressBroadcast, BroadcastStatus.FAILED)
		}
	}
}
