package com.lexfury.kkon.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.interop.UIKitView
import com.lexfury.kkon.GERMANY
import com.lexfury.kkon.LatLong
import com.lexfury.kkon.PointOfInterest
import kotlinx.cinterop.BetaInteropApi
import kotlinx.cinterop.CValue
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.collections.immutable.ImmutableList
import platform.CoreLocation.CLLocationCoordinate2D
import platform.CoreLocation.CLLocationCoordinate2DMake
import platform.MapKit.MKAnnotationView
import platform.MapKit.MKMapView
import platform.MapKit.MKMapViewDelegateProtocol
import platform.MapKit.MKPointAnnotation
import platform.darwin.NSObject

@Composable
@OptIn(ExperimentalForeignApi::class)
@BetaInteropApi
actual fun PoiMap(
	pointOfInterests: ImmutableList<PointOfInterest>,
	onPoiClicked: (String?) -> Unit,
	modifier: Modifier
) {

	val pinDelegate = remember {
		MKDelegate { annotation ->
			onPoiClicked(annotation?.subtitle)
		}
	}

	UIKitView(
		modifier = modifier,
		factory = {
			MKMapView().apply {
				showsCompass = true
				centerCoordinate = GERMANY.toAppleLocation()
				delegate = pinDelegate
			}
		},
		update = {
			val pins = pointOfInterests.map { poi ->
				val pin = MKPointAnnotation()
				val coordinates = poi.coordinates
				pin.setCoordinate(coordinates.toAppleLocation())
				pin.setTitle(poi.name)
				pin.setSubtitle(poi.id)
				pin
			}
			it.addAnnotations(pins)
		}
	)
}

@ExperimentalForeignApi
private fun LatLong.toAppleLocation(): CValue<CLLocationCoordinate2D> = CLLocationCoordinate2DMake(latitude, longitude)

@Suppress("CONFLICTING_OVERLOADS", "PARAMETER_NAME_CHANGED_ON_OVERRIDE")
private class MKDelegate(
	private val onAnnotationClicked: (MKPointAnnotation?) -> Unit
) : NSObject(), MKMapViewDelegateProtocol {

	override fun mapView(mapView: MKMapView, didSelectAnnotationView: MKAnnotationView) {
		val annotation = didSelectAnnotationView.annotation as MKPointAnnotation
		onAnnotationClicked(annotation)
	}

	override fun mapView(mapView: MKMapView, didDeselectAnnotationView: MKAnnotationView) {
		onAnnotationClicked(null)
	}
}
