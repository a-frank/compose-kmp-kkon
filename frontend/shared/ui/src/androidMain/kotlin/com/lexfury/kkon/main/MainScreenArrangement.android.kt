package com.lexfury.kkon.main

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.lexfury.kkon.PointOfInterest
import kotlinx.collections.immutable.ImmutableList

@Composable
actual fun MainScreenArrangement(
	pointOfInterests: ImmutableList<PointOfInterest>,
	selectedPoi: PointOfInterest?,
	isLoading: Boolean,
	onPoiClicked: (String?) -> Unit,
	modifier: Modifier,
) {
	MainScreenArrangementBox(
		pointOfInterests = pointOfInterests,
		selectedPoi = selectedPoi,
		isLoading = isLoading,
		onPoiClicked = onPoiClicked,
		modifier = modifier
	)
}