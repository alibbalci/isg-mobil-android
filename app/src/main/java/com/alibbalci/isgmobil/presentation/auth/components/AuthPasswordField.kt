package com.alibbalci.isgmobil.presentation.auth.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.alibbalci.isgmobil.ui.theme.BorderLight
import com.alibbalci.isgmobil.ui.theme.InputBackground
import com.alibbalci.isgmobil.ui.theme.Navy
import com.alibbalci.isgmobil.ui.theme.Orange
import com.alibbalci.isgmobil.ui.theme.TextSecondary

@Composable
fun AuthPasswordField(
    label: String = "Şifre",
    value: String,
    placeholder: String = "••••••••",
    isPasswordVisible: Boolean,
    enabled: Boolean = true,
    error: String? = null,
    onValueChange: (String) -> Unit,
    onVisibilityClick: () -> Unit
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
                    imageVector = Icons.Default.Lock,
                    contentDescription = null,
                    tint = TextSecondary.copy(
                        alpha = 0.7f
                    )
                )
            },

            trailingIcon = {

                IconButton(
                    onClick = onVisibilityClick
                ) {

                    Icon(
                        imageVector =
                            if (isPasswordVisible) {
                                Icons.Default.VisibilityOff
                            } else {
                                Icons.Default.Visibility
                            },
                        contentDescription =
                            if (isPasswordVisible) {
                                "Şifreyi gizle"
                            } else {
                                "Şifreyi göster"
                            },
                        tint = TextSecondary.copy(
                            alpha = 0.7f
                        )
                    )
                }
            },

            visualTransformation =
                if (isPasswordVisible) {
                    VisualTransformation.None
                } else {
                    PasswordVisualTransformation()
                },

            supportingText = {

                error?.let { errorMessage ->

                    Text(
                        text = errorMessage
                    )
                }
            },

            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
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