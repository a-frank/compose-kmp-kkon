package com.lexfury.kkon

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.lexfury.kkon.main.MainScreen
import com.lexfury.kkon.main.MainViewModel

@Composable
fun App(
	poiRepository: PoiRepository
) {
	MaterialTheme {
		val viewModel = remember { MainViewModel(poiRepository) }
		MainScreen(viewModel)
	}
}