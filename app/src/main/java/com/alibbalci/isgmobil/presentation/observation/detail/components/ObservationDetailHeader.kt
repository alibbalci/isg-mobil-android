package com.alibbalci.isgmobil.presentation.observation.detail.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.alibbalci.isgmobil.ui.theme.Navy
import androidx.compose.ui.graphics.Color

@Composable
fun ObservationDetailHeader(
    observationId: Long,
    createdAt: String?
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = 20.dp,
                vertical = 18.dp
            )
    ) {

        Text(
            text = "Gözlem Detayı",
            color = Color.White,
            fontWeight = FontWeight.Bold
        )

        Spacer(
            modifier = Modifier.height(6.dp)
        )

        Text(
            text = "#$observationId • ${createdAt ?: "-"}",
            color = Color.White.copy(alpha = 0.65f)
        )
    }
}