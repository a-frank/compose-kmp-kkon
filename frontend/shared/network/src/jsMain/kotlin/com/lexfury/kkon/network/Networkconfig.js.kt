package com.lexfury.kkon.network

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.js.Js
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

actual fun getHttpClientEngine(): HttpClientEngine = Js.create()

actual  fun getNetworkDispatcher(): CoroutineDispatcher = Dispatchers.Default