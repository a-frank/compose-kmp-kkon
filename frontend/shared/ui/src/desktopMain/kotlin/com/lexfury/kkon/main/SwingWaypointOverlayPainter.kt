package com.lexfury.kkon.main

import org.jxmapviewer.JXMapViewer
import org.jxmapviewer.viewer.WaypointPainter
import java.awt.Graphics2D

class SwingWaypointOverlayPainter : WaypointPainter<SwingWaypoint>() {
	override fun doPaint(g: Graphics2D, jxMapViewer: JXMapViewer, width: Int, height: Int) {
		waypoints.forEach { swingWaypoint ->
			val point = jxMapViewer.tileFactory.geoToPixel(
				swingWaypoint.position, jxMapViewer.zoom
			)
			val rectangle = jxMapViewer.viewportBounds
			val buttonX = (point.x - rectangle.getX()).toInt()
			val buttonY = (point.y - rectangle.getY()).toInt()
			val button = swingWaypoint.button
			button.setLocation(buttonX - button.width / 2, buttonY - button.height / 2)
		}
	}
}