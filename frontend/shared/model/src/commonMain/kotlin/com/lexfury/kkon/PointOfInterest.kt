package com.lexfury.kkon

data class PointOfInterest(
	val id: String,
	val coordinates: LatLong,
	val name: String,
	val details: String
)