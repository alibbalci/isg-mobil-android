package com.alibbalci.isgmobil.presentation.home.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alibbalci.isgmobil.ui.theme.BorderLight
import com.alibbalci.isgmobil.ui.theme.Navy
import com.alibbalci.isgmobil.ui.theme.Orange

@Composable
fun RecentObservationCard(
    title: String,
    location: String,
    riskText: String,
    statusText: String
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

        Column(
            modifier = Modifier.padding(14.dp)
        ) {

            Text(
                text = title,
                color = Navy,
                fontWeight = FontWeight.SemiBold
            )

            Spacer(
                modifier = Modifier.size(5.dp)
            )

            Text(
                text = location,
                color = Color.Gray,
                fontSize = 12.sp
            )

            Spacer(
                modifier = Modifier.size(10.dp)
            )

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = Color(0xFFFDE2E2)
                ) {

                    Text(
                        text = "• $riskText",
                        color = Color(0xFFDC2626),
                        fontSize = 11.sp,
                        modifier = Modifier.padding(
                            horizontal = 9.dp,
                            vertical = 5.dp
                        )
                    )
                }

                Spacer(
                    modifier = Modifier.size(6.dp)
                )

                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = Orange.copy(alpha = 0.12f)
                ) {

                    Text(
                        text = statusText,
                        color = Orange,
                        fontSize = 11.sp,
                        modifier = Modifier.padding(
                            horizontal = 9.dp,
                            vertical = 5.dp
                        )
                    )
                }
            }
        }
    }
}