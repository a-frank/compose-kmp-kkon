package com.lexfury.kkon.main

import org.jxmapviewer.viewer.DefaultWaypoint
import org.jxmapviewer.viewer.GeoPosition
import java.awt.Dimension
import java.awt.event.MouseEvent
import java.awt.event.MouseListener
import javax.swing.JButton

class SwingWaypoint(
	private val id: String,
	text: String,
	position: GeoPosition,
	private val onWaypointClicked: (String) -> Unit,
) : DefaultWaypoint(position) {

	val button = JButton(text.substring(0, 1)).apply {
		setSize(24, 24)
		preferredSize = Dimension(24, 24)
		addMouseListener(SwingWaypointMouseListener())
		isVisible = true
	}

	private inner class SwingWaypointMouseListener : MouseListener {
		override fun mouseClicked(e: MouseEvent?) {
			onWaypointClicked(id)
		}

		override fun mousePressed(e: MouseEvent?) {}
		override fun mouseReleased(e: MouseEvent?) {}
		override fun mouseEntered(e: MouseEvent?) {}
		override fun mouseExited(e: MouseEvent?) {}
	}
}