package com.alibbalci.isgmobil.presentation.auth.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alibbalci.isgmobil.ui.theme.BorderLight
import com.alibbalci.isgmobil.ui.theme.TextSecondary

@Composable
fun AuthDivider(
    text: String = "veya"
) {

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {

        HorizontalDivider(
            modifier = Modifier.weight(1f),
            color = BorderLight
        )

        Text(
            text = text,
            color = TextSecondary.copy(
                alpha = 0.65f
            ),
            fontSize = 12.sp,
            modifier = Modifier.padding(
                horizontal = 12.dp
            )
        )

        HorizontalDivider(
            modifier = Modifier.weight(1f),
            color = BorderLight
        )
    }
}