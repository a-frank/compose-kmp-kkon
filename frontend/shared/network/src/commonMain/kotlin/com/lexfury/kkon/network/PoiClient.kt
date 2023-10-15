package com.lexfury.kkon.network

import co.touchlab.kermit.Logger
import com.lexfury.kkon.datatransfer.PointOfInterestResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

private const val TAG = "PoiClient"

class PoiClient(
	private val baseUrl: String,
	private val httpClient: HttpClient,
	private val coroutineDispatcher: CoroutineDispatcher,
) {

	suspend fun getPois(): PointOfInterestResponse? = withContext(coroutineDispatcher) {
		try {
			val response = httpClient.get(" $baseUrl/vending-machines")
			if (response.status == HttpStatusCode.OK) {
				response.body<PointOfInterestResponse>()
			} else {
				null
			}
		} catch (e: Exception) {
			Logger.withTag(TAG).e("Error getting points of interest", e)
			null
		}
	}
}