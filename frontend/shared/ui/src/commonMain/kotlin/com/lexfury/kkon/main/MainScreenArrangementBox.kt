package com.lexfury.kkon.main

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.lexfury.kkon.PointOfInterest
import kotlinx.collections.immutable.ImmutableList

@Composable
fun MainScreenArrangementBox(
	pointOfInterests: ImmutableList<PointOfInterest>,
	selectedPoi: PointOfInterest?,
	isLoading: Boolean,
	onPoiClicked: (String?) -> Unit,
	modifier: Modifier = Modifier,
) {
	Box(modifier = modifier) {
		PoiMap(
			pointOfInterests = pointOfInterests,
			onPoiClicked = onPoiClicked,
			modifier = Modifier.fillMaxSize()
		)

		AnimatedVisibility(
			visible = isLoading,
			enter = slideInVertically(initialOffsetY = { -it }),
			exit = slideOutVertically(targetOffsetY = { -it }),
		) {
			LoadingIndicator()
		}

		AnimatedVisibility(
			visible = selectedPoi != null,
			enter = slideInVertically(initialOffsetY = { it }) + fadeIn(),
			exit = slideOutVertically(targetOffsetY = { it }) + fadeOut(),
			modifier = Modifier.align(Alignment.BottomCenter)
		) {
			PoiDetails(selectedPoi)
		}
	}
}