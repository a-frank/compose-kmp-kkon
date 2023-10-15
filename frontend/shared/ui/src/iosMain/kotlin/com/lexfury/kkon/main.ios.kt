package com.lexfury.kkon

import androidx.compose.ui.window.ComposeUIViewController
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

@Suppress("unused", "FunctionName") // used in Swift
fun MainViewController() = ComposeUIViewController {
	App(PoiRepositoryHelper().poiRepository)
}

class PoiRepositoryHelper : KoinComponent {
	val poiRepository: PoiRepository by inject()
}