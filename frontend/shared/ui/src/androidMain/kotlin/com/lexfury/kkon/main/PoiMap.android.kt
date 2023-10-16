package com.lexfury.kkon.main

import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.core.content.PermissionChecker
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.clustering.ClusterItem
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapsComposeExperimentalApi
import com.google.maps.android.compose.clustering.Clustering
import com.google.maps.android.compose.rememberCameraPositionState
import com.lexfury.kkon.GERMANY
import com.lexfury.kkon.LatLong
import com.lexfury.kkon.PointOfInterest
import kotlinx.collections.immutable.ImmutableList

@Composable
@MapsComposeExperimentalApi
actual fun PoiMap(
	pointOfInterests: ImmutableList<PointOfInterest>,
	onPoiClicked: (String?) -> Unit,
	modifier: Modifier
) {

	val context = LocalContext.current
	var showLocation by remember { mutableStateOf(ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PermissionChecker.PERMISSION_GRANTED) }

	val locationPermissionRequest = rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) {
		showLocation = it
	}

	LaunchedEffect(Unit) {
		locationPermissionRequest.launch(Manifest.permission.ACCESS_FINE_LOCATION)
	}

	val cameraPositionState = rememberCameraPositionState {
		position = CameraPosition(GERMANY.toGoogleLatLong(), 6f, 0f, 0f)
	}

	val items = remember(pointOfInterests) {
		pointOfInterests.map { vendingMachine ->
			PoiClusterItem(
				id = vendingMachine.id,
				position = vendingMachine.coordinates.toGoogleLatLong(),
				title = vendingMachine.name
			)
		}
	}

	GoogleMap(
		cameraPositionState = cameraPositionState,
		properties = MapProperties(isMyLocationEnabled = showLocation),
		modifier = modifier,
		onMapClick = { onPoiClicked(null) }
	) {
		Clustering(
			items = items,
			onClusterItemClick = {
				onPoiClicked(it.id)
				true
			}
		)
	}
}

private class PoiClusterItem(
	val id: String,
	private val position: LatLng,
	private val title: String,
) : ClusterItem {
	override fun getPosition(): LatLng = position

	override fun getTitle(): String = title

	override fun getSnippet(): String? = null

	override fun getZIndex(): Float? = null
}

private fun LatLong.toGoogleLatLong() = LatLng(latitude, longitude)
