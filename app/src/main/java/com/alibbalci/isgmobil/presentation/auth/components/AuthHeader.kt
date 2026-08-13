package com.alibbalci.isgmobil.presentation.auth.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alibbalci.isgmobil.ui.theme.Navy
import com.alibbalci.isgmobil.ui.theme.Orange

@Composable
fun AuthHeader(
    title: String,
    subtitle: String
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 240.dp)
            .background(Navy)
    ) {

        /*
         * Sağ üst büyük dekoratif daire
         */
        Box(
            modifier = Modifier
                .size(150.dp)
                .align(Alignment.TopEnd)
                .offset(x = 36.dp, y = (-22).dp)
                .background(
                    color = Color.White.copy(
                        alpha = 0.07f
                    ),
                    shape = CircleShape
                )
        )

        /*
         * Küçük dekoratif turuncu daire
         */
        Box(
            modifier = Modifier
                .size(62.dp)
                .align(Alignment.TopEnd)
                .offset(x = 12.dp, y = 18.dp)
                .background(
                    color = Orange.copy(
                        alpha = 0.09f
                    ),
                    shape = CircleShape
                )
        )

        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxWidth()
                .widthIn(max = 600.dp)
                .padding(
                    start = 28.dp,
                    end = 28.dp,
                    top = 34.dp,
                    bottom = 24.dp
                )
        ) {

            /*
             * Shield kutusu
             */
            Box(
                modifier = Modifier
                    .size(54.dp)
                    .border(
                        width = 1.dp,
                        color = Orange.copy(
                            alpha = 0.65f
                        ),
                        shape = RoundedCornerShape(14.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {

                Icon(
                    imageVector = Icons.Default.Shield,
                    contentDescription = null,
                    tint = Orange,
                    modifier = Modifier.size(27.dp)
                )
            }

            Spacer(
                modifier = Modifier.height(20.dp)
            )

            Text(
                text = title,
                color = Color.White,
                fontSize = 25.sp,
                lineHeight = 29.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(
                modifier = Modifier.height(7.dp)
            )

            Text(
                text = subtitle,
                color = Color.White.copy(
                    alpha = 0.58f
                ),
                fontSize = 13.sp
            )
        }
    }
}
