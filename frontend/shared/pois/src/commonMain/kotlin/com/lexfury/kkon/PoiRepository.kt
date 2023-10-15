package com.lexfury.kkon

import com.lexfury.kkon.datatransfer.LatLongDto
import com.lexfury.kkon.datatransfer.PointOfInterestDto
import com.lexfury.kkon.network.PoiClient
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class PoiRepository(
	private val poiClient: PoiClient,
	private val coroutineContext: CoroutineContext,
) {

	suspend fun getPois(): List<PointOfInterest> = withContext(coroutineContext) {
		val response = poiClient.getPois()
		response?.pois?.map {
			it.toPoi()
		}
			?: emptyList()
	}

	private fun PointOfInterestDto.toPoi(): PointOfInterest =
		PointOfInterest(
			id = id,
			coordinates = coordinates.toLatLong(),
			name = name,
			details = details
		)

	private fun LatLongDto.toLatLong(): LatLong =
		LatLong(
			latitude = latitude,
			longitude = longitude,
		)
}