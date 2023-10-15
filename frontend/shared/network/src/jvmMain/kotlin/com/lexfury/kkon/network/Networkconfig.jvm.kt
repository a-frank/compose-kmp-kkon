package com.lexfury.kkon.network

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttp
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

actual fun getHttpClientEngine(): HttpClientEngine = OkHttp.create()

actual fun getNetworkDispatcher(): CoroutineDispatcher = Dispatchers.IO