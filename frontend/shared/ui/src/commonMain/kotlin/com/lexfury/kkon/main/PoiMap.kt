package com.lexfury.kkon.main

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.lexfury.kkon.PointOfInterest
import kotlinx.collections.immutable.ImmutableList

@Composable
expect fun PoiMap(
	pointOfInterests: ImmutableList<PointOfInterest>,
	onPoiClicked: (String?) -> Unit,
	modifier: Modifier = Modifier
)