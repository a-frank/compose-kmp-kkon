package com.lexfury.kkon

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class MainActivity : ComponentActivity(), KoinComponent {

	private val poiRepository by inject<PoiRepository>()

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		setContent {
			App(poiRepository)
		}
	}
}