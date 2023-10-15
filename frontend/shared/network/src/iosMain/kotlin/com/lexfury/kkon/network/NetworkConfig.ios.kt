package com.lexfury.kkon.network

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.darwin.Darwin
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO

actual fun getHttpClientEngine(): HttpClientEngine = Darwin.create()

actual fun getNetworkDispatcher(): CoroutineDispatcher = Dispatchers.IO