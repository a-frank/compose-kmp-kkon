package com.lexfury.kkon.network

import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json

fun createHttpClient(): HttpClient =
	HttpClient(getHttpClientEngine()) {
		install(ContentNegotiation) {
			json()
		}
	}
