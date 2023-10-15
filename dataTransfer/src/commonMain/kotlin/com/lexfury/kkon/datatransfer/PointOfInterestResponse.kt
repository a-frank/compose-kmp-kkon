package com.lexfury.kkon.datatransfer

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PointOfInterestResponse(
	@SerialName("pois")
	val pois: List<PointOfInterestDto>,
)