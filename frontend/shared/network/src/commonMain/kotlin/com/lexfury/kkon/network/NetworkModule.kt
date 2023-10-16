package com.lexfury.kkon.network

import org.koin.dsl.module

val networkModule = module {
	single {
		createHttpClient()
	}

	single {
		PoiClient(
			baseUrl = "my.server.com",
			httpClient = get(),
			coroutineDispatcher = getNetworkDispatcher()
		)
	}
}