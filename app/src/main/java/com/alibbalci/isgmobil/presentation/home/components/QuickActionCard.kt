package com.alibbalci.isgmobil.presentation.home.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.alibbalci.isgmobil.ui.theme.CardBackground
import com.alibbalci.isgmobil.ui.theme.InputBackground

@Composable
fun QuickActionCard(
    title: String,
    icon: ImageVector,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {

    Surface(
        modifier = modifier
            .clickable {
                onClick()
            },
        shape = RoundedCornerShape(14.dp),
        color = CardBackground,
        border = BorderStroke(
            width = 1.dp,
            color = BorderLight
        )
    ) {

        Row(
            modifier = Modifier.padding(14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Surface(
                shape = RoundedCornerShape(10.dp),
                color = InputBackground
            ) {

                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = Navy,
                    modifier = Modifier
                        .padding(8.dp)
                        .size(18.dp)
                )
            }

            Spacer(
                modifier = Modifier.size(10.dp)
            )

            Text(
                text = title,
                color = Navy,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.weight(1f)
            )
        }
    }
}
