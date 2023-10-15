package com.lexfury.kkon.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier

@Composable
fun MainScreen(viewModel: MainViewModel) {
	val state by viewModel.state.collectAsState()
	MainScreenArrangement(
		pointOfInterests = state.pointOfInterests,
		selectedPoi = state.selectedPoi,
		isLoading = state.loading,
		onPoiClicked = viewModel::onPoiClicked,
		modifier = Modifier.fillMaxSize()
	)
}
