@file:JvmName("KoinInitializer")

package com.lexfury.kkon

import org.koin.core.context.startKoin
import kotlin.jvm.JvmName

fun initKoin() =
	startKoin {
		modules(poisModule)
	}