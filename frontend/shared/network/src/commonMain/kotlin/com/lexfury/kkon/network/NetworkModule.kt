package com.lexfury.kkon.network

import org.koin.dsl.module

val networkModule = module {
	single {
		createHttpClient()
	}

	single {
		PoiClient(
			baseUrl = "",
			httpClient = get(),
			coroutineDispatcher = getNetworkDispatcher()
		)
	}
}