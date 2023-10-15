package com.lexfury.kkon.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun LoadingIndicator(modifier: Modifier = Modifier) {
	Row(
		modifier = modifier
			.fillMaxWidth()
			.clip(RoundedCornerShape(bottomStart = 8.dp, bottomEnd = 8.dp))
			.background(Color(0xFFFF8000))
			.padding(8.dp),
		verticalAlignment = Alignment.CenterVertically,
	) {
		CircularProgressIndicator(modifier = Modifier.size(20.dp))
		Spacer(modifier = Modifier.size(8.dp))
		Text(text = "Loading…", color = Color.DarkGray)
	}
}
