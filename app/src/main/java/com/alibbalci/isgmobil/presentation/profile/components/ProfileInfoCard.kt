package com.alibbalci.isgmobil.presentation.profile.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.unit.sp
import com.alibbalci.isgmobil.ui.theme.BorderLight
import com.alibbalci.isgmobil.ui.theme.Navy
import com.alibbalci.isgmobil.ui.theme.Orange
import com.alibbalci.isgmobil.ui.theme.TextSecondary

@Composable
fun ProfileInfoCard(
    title: String,
    value: String,
    icon: ImageVector
) {

    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(14.dp),
        color = Color.White,
        border = BorderStroke(
            width = 1.dp,
            color = BorderLight
        )
    ) {

        Row(
            modifier = Modifier.padding(16.dp),
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

            Column {

                Text(
                    text = title,
                    color = TextSecondary,
                    fontSize = 12.sp
                )

                Spacer(
                    modifier = Modifier.size(3.dp)
                )

                Text(
                    text = value,
                    color = Navy,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}