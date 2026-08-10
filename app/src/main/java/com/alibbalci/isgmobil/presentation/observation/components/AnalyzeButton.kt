package com.alibbalci.isgmobil.presentation.observation.create.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.alibbalci.isgmobil.ui.theme.Orange

@Composable
fun AnalyzeButton(
    enabled: Boolean,
    isAnalyzing: Boolean,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        enabled = enabled && !isAnalyzing,
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        shape = RoundedCornerShape(14.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Orange,
            contentColor = Color.White,
            disabledContainerColor = Orange.copy(alpha = 0.40f),
            disabledContentColor = Color.White.copy(alpha = 0.75f)
        )
    ) {

        if (isAnalyzing) {

            CircularProgressIndicator(
                modifier = Modifier.size(22.dp),
                color = Color.White,
                strokeWidth = 2.dp
            )

            Spacer(
                modifier = Modifier.size(10.dp)
            )

            Text(
                text = "Analiz ediliyor...",
                style = MaterialTheme.typography.labelLarge
            )

        } else {

            Text(
                text = "Analiz Et",
                style = MaterialTheme.typography.labelLarge
            )
        }
    }
}