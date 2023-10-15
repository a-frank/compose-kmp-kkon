package com.lexfury.kkon

import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.server.plugins.callloging.CallLogging
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import io.ktor.server.routing.Routing
import org.slf4j.event.Level

fun main() {
	embeddedServer(
		factory = Netty,
		port = 8080,
		module = Application::modules
	).start(wait = true)
}

private fun Application.modules() {
	install(Routing)
	install(CallLogging) {
		level = Level.INFO
	}
	install(ContentNegotiation) {
		json()
	}
	poiRoute(PointOfInterestRepository())
}