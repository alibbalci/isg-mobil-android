package com.alibbalci.isgmobil.presentation.profile.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
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
fun ProfileHeader(
    fullName: String,
    role: String
) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 220.dp)
            .background(Navy)
    ) {

        Box(
            modifier = Modifier
                .size(160.dp)
                .align(Alignment.TopEnd)
                .offset(x = 34.dp, y = (-40).dp)
                .background(
                    Color.White.copy(alpha = 0.06f),
                    CircleShape
                )
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp, start = 20.dp, end = 20.dp, bottom = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Surface(
                modifier = Modifier.size(76.dp),
                shape = CircleShape,
                color = Orange.copy(alpha = 0.16f)
            ) {

                Box(
                    contentAlignment = Alignment.Center
                ) {

                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = null,
                        tint = Orange,
                        modifier = Modifier.size(38.dp)
                    )
                }
            }

            Spacer(
                modifier = Modifier.height(14.dp)
            )

            Text(
                text = fullName.ifBlank {
                    "Kullanıcı"
                },
                color = Color.White,
                fontSize = 21.sp,
                fontWeight = FontWeight.Bold,
                maxLines = 2
            )

            Spacer(
                modifier = Modifier.height(5.dp)
            )

            Text(
                text = formatProfileRole(role),
                color = Color.White.copy(alpha = 0.65f),
                fontSize = 13.sp
            )
        }
    }
}

private fun formatProfileRole(
    role: String
): String {

    return when (role.uppercase()) {

        "ADMIN" -> "Yönetici"

        "ISG_EXPERT",
        "OHS_EXPERT" -> "İSG Uzmanı"

        "USER" -> "Kullanıcı"

        else ->
            role.replace("_", " ")
                .lowercase()
                .replaceFirstChar {
                    it.uppercase()
                }
    }
}
