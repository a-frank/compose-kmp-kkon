package com.lexfury.kkon.main

import co.touchlab.kermit.Logger
import com.lexfury.kkon.PoiRepository
import com.lexfury.kkon.PointOfInterest
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.stateIn

class MainViewModel(
	private val poiRepository: PoiRepository
) {

	private val selectedPoi = MutableStateFlow<PointOfInterest?>(null)

	val state: StateFlow<MainViewState> = flow {
		val pointOfInterests = poiRepository.getPois()
		emit(pointOfInterests.toImmutableList())
	}.combine(selectedPoi) { pointOfInterests, selectedPoi ->
		MainViewState(
			pointOfInterests = pointOfInterests,
			selectedPoi = selectedPoi,
			loading = false
		)
	}
		.flowOn(Dispatchers.Default)
		.stateIn(
			scope = CoroutineScope(Dispatchers.Main),
			started = SharingStarted.WhileSubscribed(5_000),
			initialValue = MainViewState()
		)

	fun onPoiClicked(poiId: String?) {
		val poi = state.value.pointOfInterests.find { it.id == poiId }
		log.d { "Poi $poiId selected, $poi found in pool" }
		selectedPoi.value = poi
	}

	companion object {
		private const val TAG = "MainViewModel"
		val log: Logger = Logger.withTag(TAG)
	}
}