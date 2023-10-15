package com.lexfury.kkon.main

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lexfury.kkon.PointOfInterest

@Composable
fun PoiDetails(
	pointOfInterest: PointOfInterest?,
	modifier: Modifier = Modifier,

	) {

	Row(
		modifier = modifier
			.fillMaxWidth()
			.clip(RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp))
			.background(Color.Black.copy(alpha = .8f))
			.padding(16.dp)
			.animateContentSize(),
		verticalAlignment = Alignment.CenterVertically,
	) {
		AnimatedContent(
			targetState = pointOfInterest,
			transitionSpec = { fadeIn().togetherWith(fadeOut()) }
		) {
			Column {
				Text(text = it?.name ?: "", color = Color.White)
				Spacer(modifier = Modifier.size(4.dp))
				Text(text = it?.details ?: "", color = Color.LightGray, fontSize = 12.sp)
			}
		}
	}
}