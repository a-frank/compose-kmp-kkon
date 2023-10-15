package com.lexfury.kkon.main

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.lexfury.kkon.PointOfInterest
import kotlinx.collections.immutable.ImmutableList

@Composable
fun MainScreenArrangementColumn(
	pointOfInterests: ImmutableList<PointOfInterest>,
	selectedPoi: PointOfInterest?,
	isLoading: Boolean,
	onPoiClicked: (String?) -> Unit,
	modifier: Modifier = Modifier,
) {
	Column(modifier = modifier) {
		AnimatedVisibility(
			visible = isLoading,
			enter = slideInVertically(initialOffsetY = { -it }),
			exit = slideOutVertically(targetOffsetY = { -it }),
		) {
			LoadingIndicator()
		}
		PoiMap(
			pointOfInterests = pointOfInterests,
			onPoiClicked = onPoiClicked,
			modifier = Modifier
				.animateContentSize()
				.fillMaxSize()
				.weight(1f),
		)

		AnimatedVisibility(
			visible = selectedPoi != null,
			enter = slideInVertically(initialOffsetY = { it }) + fadeIn(),
			exit = slideOutVertically(targetOffsetY = { it }) + fadeOut(),
		) {
			PoiDetails(
				pointOfInterest = selectedPoi,
			)
		}
	}
}