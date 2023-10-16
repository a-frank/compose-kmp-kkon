package com.lexfury.kkon

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import org.jetbrains.compose.web.dom.Br
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Text
import org.jetbrains.compose.web.renderComposable
import org.koin.core.context.startKoin

fun main() {
	val koinApplication = startKoin {
		modules(vendingMachinesModule)
	}
	val repository = koinApplication.koin.get<PoiRepository>()

	renderComposable(rootElementId = "app") {
		Div {
			var pois by remember { mutableStateOf<List<PointOfInterest>>(emptyList()) }
			LaunchedEffect(Unit) {
				pois = repository.getPois()
			}
			Text("Hello, KKon!")
			Br()
			pois.forEach {
				Text(it.name)
				Br()
			}
		}
	}
}