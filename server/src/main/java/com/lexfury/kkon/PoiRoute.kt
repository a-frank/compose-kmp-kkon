package com.lexfury.kkon

import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.server.response.header
import io.ktor.server.response.respond
import io.ktor.server.routing.get
import io.ktor.server.routing.routing

fun Application.poiRoute(pointOfInterestRepository: PointOfInterestRepository) {
	routing {
		get("/pois") {
			call.response.header("Access-Control-Allow-Origin", "*")
			call.respond(pointOfInterestRepository.getPointsOfInterest())
		}
	}
}