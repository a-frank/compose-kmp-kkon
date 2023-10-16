package com.lexfury.kkon

import com.lexfury.kkon.network.networkModule
import org.koin.dsl.module

private val internalPoiModule = module {

	single { PoiRepository(get(), getRepositoryDispatcher()) }
}

val poisModule = listOf(networkModule, internalPoiModule)