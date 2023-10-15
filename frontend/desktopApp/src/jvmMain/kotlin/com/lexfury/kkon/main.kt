package com.lexfury.kkon

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
	val koinApplication = initKoin()
	val repository = koinApplication.koin.get<PoiRepository>()

	Window(
		title = "POI Explorer",
		onCloseRequest = ::exitApplication,
	) {
		App(repository)
	}
}