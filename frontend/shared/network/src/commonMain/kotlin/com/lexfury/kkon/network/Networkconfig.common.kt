package com.lexfury.kkon.network

import io.ktor.client.engine.HttpClientEngine
import kotlinx.coroutines.CoroutineDispatcher

expect fun getHttpClientEngine(): HttpClientEngine

expect fun getNetworkDispatcher(): CoroutineDispatcher