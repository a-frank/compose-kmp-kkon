package com.lexfury.kkon

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO

actual fun getRepositoryDispatcher(): CoroutineDispatcher = Dispatchers.IO