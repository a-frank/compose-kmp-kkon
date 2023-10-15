package com.lexfury.kkon.main

import com.lexfury.kkon.PointOfInterest
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

data class MainViewState(
	val pointOfInterests: ImmutableList<PointOfInterest> = persistentListOf(),
	val selectedPoi: PointOfInterest? = null,
	val loading: Boolean = true,
)