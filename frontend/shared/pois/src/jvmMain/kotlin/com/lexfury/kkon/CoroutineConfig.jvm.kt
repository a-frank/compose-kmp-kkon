package com.lexfury.kkon

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

actual fun getRepositoryDispatcher(): CoroutineDispatcher = Dispatchers.IO