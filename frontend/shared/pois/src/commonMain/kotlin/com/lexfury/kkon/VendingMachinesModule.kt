package com.lexfury.kkon

import com.lexfury.kkon.network.networkModule
import org.koin.dsl.module

private val internalVendingMachinesModule = module {

	single { PoiRepository(get(), getRepositoryDispatcher()) }
}

val vendingMachinesModule = listOf(networkModule, internalVendingMachinesModule)