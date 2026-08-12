package com.alibbalci.isgmobil.presentation.profile.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.alibbalci.isgmobil.ui.theme.BorderLight
import com.alibbalci.isgmobil.ui.theme.Navy
import com.alibbalci.isgmobil.ui.theme.Orange
import com.alibbalci.isgmobil.ui.theme.TextSecondary

@Composable
fun ProfileActionItem(
    title: String,
    icon: ImageVector,
    modifier: Modifier = Modifier,
    subtitle: String? = null,
    onClick: () -> Unit
) {

    Surface(
        modifier = modifier
            .fillMaxWidth()
            .clickable {
                onClick()
            },
        shape = RoundedCornerShape(14.dp),
        color = Color.White,
        border = BorderStroke(
            width = 1.dp,
            color = BorderLight
        )
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Surface(
                shape = RoundedCornerShape(10.dp),
                color = Orange.copy(alpha = 0.10f)
            ) {

                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = Orange,
                    modifier = Modifier
                        .padding(9.dp)
                        .size(20.dp)
                )
            }

            Spacer(
                modifier = Modifier.size(12.dp)
            )

            androidx.compose.foundation.layout.Column(
                modifier = Modifier.weight(1f)
            ) {

                Text(
                    text = title,
                    color = Navy,
                    fontWeight = FontWeight.SemiBold
                )

                if (!subtitle.isNullOrBlank()) {

                    Spacer(
                        modifier = Modifier.size(3.dp)
                    )

                    Text(
                        text = subtitle,
                        color = TextSecondary
                    )
                }
            }

            Icon(
                imageVector = Icons.Default.ChevronRight,
                contentDescription = null,
                tint = TextSecondary.copy(alpha = 0.65f)
            )
        }
    }
}