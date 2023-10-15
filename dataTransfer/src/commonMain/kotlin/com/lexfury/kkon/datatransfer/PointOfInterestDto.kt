package com.lexfury.kkon.datatransfer

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PointOfInterestDto(
	@SerialName("id")
	val id: String,
	@SerialName("coordinates")
	val coordinates: LatLongDto,
	@SerialName("name")
	val name: String,
	@SerialName("details")
	val details: String
)