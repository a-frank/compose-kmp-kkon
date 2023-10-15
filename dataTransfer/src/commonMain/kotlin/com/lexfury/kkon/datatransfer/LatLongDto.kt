package com.lexfury.kkon.datatransfer

import kotlinx.serialization.Serializable

@Serializable
data class LatLongDto(
	val latitude: Double,
	val longitude: Double
)