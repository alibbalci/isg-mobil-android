package com.alibbalci.isgmobil.presentation.auth.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.alibbalci.isgmobil.ui.theme.Orange

@Composable
fun AuthSecondaryButton(
    text: String,
    enabled: Boolean = true,
    onClick: () -> Unit
) {

    OutlinedButton(
        onClick = onClick,
        enabled = enabled,
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(
            width = 1.dp,
            color = Orange
        ),
        colors = ButtonDefaults.outlinedButtonColors(
            contentColor = Orange
        )
    ) {

        Text(
            text = text,
            style = MaterialTheme.typography.labelLarge
        )
    }
}