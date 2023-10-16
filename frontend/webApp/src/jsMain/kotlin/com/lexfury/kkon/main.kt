package com.lexfury.kkon

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import kotlinx.coroutines.delay
import org.jetbrains.compose.web.dom.Br
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.H1
import org.jetbrains.compose.web.dom.Progress
import org.jetbrains.compose.web.dom.Table
import org.jetbrains.compose.web.dom.Td
import org.jetbrains.compose.web.dom.Text
import org.jetbrains.compose.web.dom.Th
import org.jetbrains.compose.web.dom.Tr
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
				delay(2_000)
				pois = repository.getPois()
			}
			H1 {
				Text("Hello, KKon!")
			}
			Br()
			Br()

			if (pois.isEmpty()) {
				Progress()
			} else {
				Table {
					Tr {
						Th {
							Text("Name")
						}
						Th {
							Text("Details")
						}
					}

					pois.forEach {
						Tr {
							Td {
								Text(it.name)
							}
							Td {
								Text(it.details)
							}

						}
					}
				}
			}
		}
	}
}