package com.lexfury.kkon

import com.lexfury.kkon.datatransfer.LatLongDto
import com.lexfury.kkon.datatransfer.PointOfInterestDto
import com.lexfury.kkon.datatransfer.PointOfInterestResponse

class PointOfInterestRepository {
	fun getPointsOfInterest(): PointOfInterestResponse = poiResponse
}

private val poiResponse: PointOfInterestResponse = PointOfInterestResponse(
	listOf(
		PointOfInterestDto(
			"1",
			coordinates = LatLongDto(52.5200, 13.4050),
			"Berlin",
			"Hauptstadt"
		),
		PointOfInterestDto(
			"2",
			coordinates = LatLongDto(50.1109, 8.6821),
			"Frankfurt",
			"Mainhatten"
		),
		PointOfInterestDto(
			"3",
			coordinates = LatLongDto(49.0069, 8.4037),
			"Karlsruhe",
			"Bundesverfassungsgericht"
		),
		PointOfInterestDto(
			"4",
			coordinates = LatLongDto(53.5488, 9.9872),
			"Hamburg",
			"Fischmarkt"
		),
	)
)