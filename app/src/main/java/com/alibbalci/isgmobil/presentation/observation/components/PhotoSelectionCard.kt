package com.alibbalci.isgmobil.presentation.observation.create.components

import android.net.Uri
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.alibbalci.isgmobil.ui.theme.BorderLight
import com.alibbalci.isgmobil.ui.theme.InputBackground
import com.alibbalci.isgmobil.ui.theme.Navy
import com.alibbalci.isgmobil.ui.theme.Orange
import com.alibbalci.isgmobil.ui.theme.TextSecondary

@Composable
fun PhotoSelectionCard(
    photoUri: Uri?,
    onTakePhoto: () -> Unit,
    onPickFromGallery: () -> Unit,
    onChangePhoto: () -> Unit,
    onRemovePhoto: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {

        Text(
            text = "Fotoğraf *",
            color = Navy,
            style = MaterialTheme.typography.titleSmall
        )

        Spacer(
            modifier = Modifier.height(10.dp)
        )

        if (photoUri == null) {

            EmptyPhotoArea(
                onTakePhoto = onTakePhoto,
                onPickFromGallery = onPickFromGallery
            )

        } else {

            SelectedPhotoArea(
                photoUri = photoUri,
                onChangePhoto = onChangePhoto,
                onRemovePhoto = onRemovePhoto
            )
        }
    }
}

@Composable
private fun EmptyPhotoArea(
    onTakePhoto: () -> Unit,
    onPickFromGallery: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = BorderLight,
                shape = RoundedCornerShape(16.dp)
            )
            .background(
                color = InputBackground,
                shape = RoundedCornerShape(16.dp)
            )
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Fotoğraf ekleyin",
            color = Navy,
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(
            modifier = Modifier.height(6.dp)
        )

        Text(
            text = "Tehlikeli durumu kamerayla çekin veya galeriden seçin.",
            color = TextSecondary,
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(
            modifier = Modifier.height(18.dp)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {

            Surface(
                modifier = Modifier
                    .weight(1f)
                    .clickable {
                        onTakePhoto()
                    },
                shape = RoundedCornerShape(12.dp),
                color = Orange
            ) {

                Text(
                    text = "Fotoğraf Çek",
                    color = Color.White,
                    style = MaterialTheme.typography.labelLarge,
                    modifier = Modifier.padding(
                        vertical = 14.dp,
                        horizontal = 12.dp
                    )
                )
            }

            Surface(
                modifier = Modifier
                    .weight(1f)
                    .clickable {
                        onPickFromGallery()
                    },
                shape = RoundedCornerShape(12.dp),
                color = Color.White,
                border = BorderStroke(
                    width = 1.dp,
                    color = Orange
                )
            ) {

                Text(
                    text = "Galeriden Seç",
                    color = Orange,
                    style = MaterialTheme.typography.labelLarge,
                    modifier = Modifier.padding(
                        vertical = 14.dp,
                        horizontal = 12.dp
                    )
                )
            }
        }
    }
}

@Composable
private fun SelectedPhotoArea(
    photoUri: Uri,
    onChangePhoto: () -> Unit,
    onRemovePhoto: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(230.dp)
            .clip(
                RoundedCornerShape(16.dp)
            )
    ) {

        AsyncImage(
            model = photoUri,
            contentDescription = "Seçilen gözlem fotoğrafı",
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.Crop
        )

        Row(
            modifier = Modifier
                .align(
                    Alignment.BottomCenter
                )
                .fillMaxWidth()
                .background(
                    Color.Black.copy(
                        alpha = 0.45f
                    )
                )
                .padding(12.dp),
            horizontalArrangement =
                Arrangement.End,
            verticalAlignment =
                Alignment.CenterVertically
        ) {

            Surface(
                modifier = Modifier
                    .clickable {
                        onRemovePhoto()
                    },
                shape = RoundedCornerShape(10.dp),
                color = Color.White.copy(
                    alpha = 0.95f
                )
            ) {

                Text(
                    text = "Kaldır",
                    color = Navy,
                    style =
                        MaterialTheme
                            .typography
                            .labelMedium,
                    modifier =
                        Modifier.padding(
                            horizontal = 14.dp,
                            vertical = 9.dp
                        )
                )
            }

            Spacer(
                modifier = Modifier.width(8.dp)
            )

            Surface(
                modifier = Modifier
                    .clickable {
                        onChangePhoto()
                    },
                shape = RoundedCornerShape(10.dp),
                color = Orange
            ) {

                Text(
                    text = "Değiştir",
                    color = Color.White,
                    style =
                        MaterialTheme
                            .typography
                            .labelMedium,
                    modifier =
                        Modifier.padding(
                            horizontal = 14.dp,
                            vertical = 9.dp
                        )
                )
            }
        }
    }
}