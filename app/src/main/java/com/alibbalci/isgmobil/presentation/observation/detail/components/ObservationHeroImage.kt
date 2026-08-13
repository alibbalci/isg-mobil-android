package com.alibbalci.isgmobil.presentation.observation.detail.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun ObservationHeroImage(
    photoUrl: String?
) {

    if (!photoUrl.isNullOrBlank()) {

        AsyncImage(
            model = photoUrl,
            contentDescription = "Gözlem fotoğrafı",
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(16f / 9f)
                .clip(
                    RoundedCornerShape(18.dp)
                ),
            contentScale = ContentScale.Crop
        )
    }
}
