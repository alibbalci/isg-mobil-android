package com.alibbalci.isgmobil.presentation.auth.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.alibbalci.isgmobil.ui.theme.BorderLight
import com.alibbalci.isgmobil.ui.theme.InputBackground
import com.alibbalci.isgmobil.ui.theme.Navy
import com.alibbalci.isgmobil.ui.theme.Orange
import com.alibbalci.isgmobil.ui.theme.TextSecondary

@Composable
fun AuthTextField(
    label: String,
    value: String,
    placeholder: String,
    icon: ImageVector,
    enabled: Boolean = true,
    error: String? = null,
    keyboardType: KeyboardType = KeyboardType.Text,
    onValueChange: (String) -> Unit
) {

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {

        Text(
            text = label,
            color = Navy,
            style = MaterialTheme.typography.titleSmall
        )

        Spacer(
            modifier = Modifier.height(8.dp)
        )

        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            enabled = enabled,
            singleLine = true,
            isError = error != null,
            placeholder = {

                Text(
                    text = placeholder
                )
            },
            leadingIcon = {

                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = TextSecondary.copy(
                        alpha = 0.7f
                    )
                )
            },
            supportingText = {

                error?.let { errorMessage ->

                    Text(
                        text = errorMessage
                    )
                }
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = keyboardType
            ),
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = InputBackground,
                unfocusedContainerColor = InputBackground,
                disabledContainerColor = InputBackground,

                focusedBorderColor = Orange,
                unfocusedBorderColor = BorderLight,

                errorBorderColor =
                    MaterialTheme.colorScheme.error,

                focusedTextColor = Navy,
                unfocusedTextColor = Navy,

                focusedPlaceholderColor =
                    TextSecondary.copy(alpha = 0.65f),

                unfocusedPlaceholderColor =
                    TextSecondary.copy(alpha = 0.65f),

                cursorColor = Orange
            ),
            modifier = Modifier.fillMaxWidth()
        )
    }
}