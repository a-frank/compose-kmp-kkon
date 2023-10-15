package com.lexfury.kkon

import android.app.Application

class CubeApplication : Application() {
	override fun onCreate() {
		super.onCreate()
		initKoin()
	}
}