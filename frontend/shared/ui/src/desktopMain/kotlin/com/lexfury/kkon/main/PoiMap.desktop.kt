package com.lexfury.kkon.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.awt.SwingPanel
import com.lexfury.kkon.GERMANY
import com.lexfury.kkon.LatLong
import com.lexfury.kkon.PointOfInterest
import kotlinx.collections.immutable.ImmutableList
import org.jxmapviewer.JXMapViewer
import org.jxmapviewer.OSMTileFactoryInfo
import org.jxmapviewer.cache.FileBasedLocalCache
import org.jxmapviewer.input.CenterMapListener
import org.jxmapviewer.input.PanMouseInputListener
import org.jxmapviewer.input.ZoomMouseWheelListenerCursor
import org.jxmapviewer.viewer.DefaultTileFactory
import org.jxmapviewer.viewer.GeoPosition
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent
import java.io.File

@Composable
actual fun PoiMap(
	pointOfInterests: ImmutableList<PointOfInterest>,
	onPoiClicked: (String?) -> Unit,
	modifier: Modifier
) {

	val waypoints = remember(pointOfInterests) {
		pointOfInterests.map {
			SwingWaypoint(
				id = it.id,
				text = it.name,
				position = it.coordinates.toDesktopPosition(),
				onWaypointClicked = onPoiClicked
			)
		}
			.toSet()
	}

	// SwingPanel overrides compose components at the moment
	SwingPanel(
		factory = {
			val cacheDir = File(System.getProperty("user.home") + File.separator + ".jxmapviewer2")

			JXMapViewer().apply {
				tileFactory = DefaultTileFactory(OSMTileFactoryInfo()).also {
					it.setThreadPoolSize(8)
					it.setLocalCache(FileBasedLocalCache(cacheDir, false))
				}

				val mia = PanMouseInputListener(this)
				addMouseListener(mia)
				addMouseMotionListener(mia)
				addMouseListener(CenterMapListener(this))
				addMouseWheelListener(ZoomMouseWheelListenerCursor(this))
				addMouseListener(object : MouseAdapter() {
					override fun mouseClicked(mouseEvent: MouseEvent?) {
						onPoiClicked(null)
					}
				})

				overlayPainter = SwingWaypointOverlayPainter().also {
					it.waypoints = waypoints
				}

				addressLocation = GERMANY.toDesktopPosition()
			}
		},
		update = { mapViewer ->
			mapViewer.removeAll()
			waypoints.forEach {
				mapViewer.add(it.button)
			}
			mapViewer.zoomToBestFit(waypoints.map { it.position }.toSet(), .7)
		},
		modifier = modifier
	)
}

private fun LatLong.toDesktopPosition(): GeoPosition = GeoPosition(latitude, longitude)