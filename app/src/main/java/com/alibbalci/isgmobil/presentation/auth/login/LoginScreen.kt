package com.alibbalci.isgmobil.presentation.auth.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.alibbalci.isgmobil.presentation.auth.components.AuthDivider
import com.alibbalci.isgmobil.presentation.auth.components.AuthHeader
import com.alibbalci.isgmobil.presentation.auth.components.AuthPasswordField
import com.alibbalci.isgmobil.presentation.auth.components.AuthPrimaryButton
import com.alibbalci.isgmobil.presentation.auth.components.AuthSecondaryButton
import com.alibbalci.isgmobil.presentation.auth.components.AuthTextField
import com.alibbalci.isgmobil.ui.theme.Orange

@Composable
fun LoginScreen(
    viewModel: LoginViewModel,
    onNavigateToRegister: () -> Unit,
    onNavigateToHome: () -> Unit
) {

    val uiState by viewModel.uiState.collectAsState()

    /*
     * LOGIN BAŞARILI OLUNCA
     * HOME EKRANINA GİT
     */
    LaunchedEffect(viewModel) {

        viewModel.effect.collect { effect ->

            when (effect) {

                LoginEffect.NavigateToHome -> {
                    onNavigateToHome()
                }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(
                rememberScrollState()
            )
    ) {

        /*
         * HEADER
         */
        AuthHeader(
            title = "Tekrar\nHoş Geldiniz",
            subtitle = "İş güvenliğine devam etmek için giriş yapın"
        )

        /*
         * FORM ALANI
         */
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 28.dp,
                    vertical = 28.dp
                )
        ) {

            /*
             * E-POSTA
             */
            AuthTextField(
                label = "E-posta Adresi",
                value = uiState.email,
                placeholder = "ornek@sirket.com",
                icon = Icons.Default.MailOutline,
                enabled = !uiState.isLoading,
                error = uiState.emailError,
                keyboardType = KeyboardType.Email,
                onValueChange = viewModel::onEmailChange
            )

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            /*
             * ŞİFRE
             */
            AuthPasswordField(
                value = uiState.password,
                isPasswordVisible = uiState.isPasswordVisible,
                enabled = !uiState.isLoading,
                error = uiState.passwordError,
                onValueChange = viewModel::onPasswordChange,
                onVisibilityClick =
                    viewModel::togglePasswordVisibility
            )

            /*
             * ŞİFREMİ UNUTTUM
             *
             * Şimdilik sadece görsel.
             * Daha sonra forgot-password
             * route bağlayabiliriz.
             */
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {

                Spacer(
                    modifier = Modifier.weight(1f)
                )

                TextButton(
                    onClick = {
                        // TODO: Forgot password
                    },
                    enabled = !uiState.isLoading
                ) {

                    Text(
                        text = "Şifremi Unuttum",
                        color = Orange
                    )
                }
            }

            /*
             * GENEL HATA
             */
            uiState.generalError?.let { error ->

                Spacer(
                    modifier = Modifier.height(4.dp)
                )

                Text(
                    text = error,
                    color =
                        MaterialTheme
                            .colorScheme
                            .error,
                    style =
                        MaterialTheme
                            .typography
                            .bodyMedium
                )
            }

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            /*
             * GİRİŞ YAP
             */
            AuthPrimaryButton(
                text = "Giriş Yap",
                isLoading = uiState.isLoading,
                enabled = true,
                onClick = viewModel::login
            )

            Spacer(
                modifier = Modifier.height(22.dp)
            )

            /*
             * VEYA
             */
            AuthDivider()

            Spacer(
                modifier = Modifier.height(22.dp)
            )

            /*
             * REGISTER
             */
            AuthSecondaryButton(
                text = "Yeni Hesap Oluştur",
                enabled = !uiState.isLoading,
                onClick = onNavigateToRegister
            )

            Spacer(
                modifier = Modifier.height(24.dp)
            )

            /*
             * ALT BİLGİ
             */
            Text(
                text =
                    "Giriş yaparak Kullanım Koşullarını kabul etmiş olursunuz.",
                style =
                    MaterialTheme
                        .typography
                        .bodySmall,
                modifier =
                    Modifier.fillMaxWidth()
            )

            Spacer(
                modifier = Modifier.height(20.dp)
            )
        }
    }
}