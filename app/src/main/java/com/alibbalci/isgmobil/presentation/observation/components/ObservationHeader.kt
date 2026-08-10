package com.alibbalci.isgmobil.presentation.observation.create.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.alibbalci.isgmobil.ui.theme.HeaderSubtitle
import com.alibbalci.isgmobil.ui.theme.Navy

@Composable
fun ObservationHeader(
    onBack: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Navy)
            .padding(
                start = 20.dp,
                end = 20.dp,
                top = 22.dp,
                bottom = 28.dp
            )
    ) {

        Surface(
            modifier = Modifier
                .size(36.dp)
                .clickable {
                    onBack()
                },
            shape = RoundedCornerShape(10.dp),
            color = Color.White.copy(alpha = 0.12f)
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = "‹",
                    color = Color.White,
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(start = 12.dp)
                )
            }
        }

        Spacer(
            modifier = Modifier.height(22.dp)
        )

        Text(
            text = "Yeni Gözlem",
            color = Color.White,
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(
            modifier = Modifier.height(6.dp)
        )

        Text(
            text = "Tehlikeyi belgeleyin ve analiz edin",
            color = HeaderSubtitle,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}