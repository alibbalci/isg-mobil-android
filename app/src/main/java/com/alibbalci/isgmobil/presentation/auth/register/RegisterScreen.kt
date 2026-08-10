package com.alibbalci.isgmobil.presentation.auth.register

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.alibbalci.isgmobil.presentation.auth.components.AuthDivider
import com.alibbalci.isgmobil.presentation.auth.components.AuthHeader
import com.alibbalci.isgmobil.presentation.auth.components.AuthPasswordField
import com.alibbalci.isgmobil.presentation.auth.components.AuthPrimaryButton
import com.alibbalci.isgmobil.presentation.auth.components.AuthSecondaryButton
import com.alibbalci.isgmobil.presentation.auth.components.AuthTextField

@Composable
fun RegisterScreen(
    viewModel: RegisterViewModel,
    onNavigateToLogin: () -> Unit
) {

    val uiState by viewModel.uiState
        .collectAsStateWithLifecycle()

    /*
     * KAYIT BAŞARILI OLUNCA
     * LOGIN EKRANINA DÖN
     */
    LaunchedEffect(viewModel) {

        viewModel.effect.collect { effect ->

            when (effect) {

                RegisterEffect.NavigateToLogin -> {
                    onNavigateToLogin()
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
            title = "Hesabınızı\nOluşturun",
            subtitle = "İş güvenliğini birlikte daha güçlü hale getirin"
        )

        /*
         * FORM
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
             * AD SOYAD
             */
            AuthTextField(
                label = "Ad Soyad",
                value = uiState.fullName,
                placeholder = "Adınızı ve soyadınızı girin",
                icon = Icons.Default.Person,
                enabled = !uiState.isLoading,
                error = uiState.fullNameError,
                keyboardType = KeyboardType.Text,
                onValueChange =
                    viewModel::onFullNameChange
            )

            Spacer(
                modifier = Modifier.height(16.dp)
            )

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
                onValueChange =
                    viewModel::onEmailChange
            )

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            /*
             * ŞİFRE
             */
            AuthPasswordField(
                value = uiState.password,
                isPasswordVisible =
                    uiState.isPasswordVisible,
                enabled = !uiState.isLoading,
                error = uiState.passwordError,
                onValueChange =
                    viewModel::onPasswordChange,
                onVisibilityClick =
                    viewModel::togglePasswordVisibility
            )

            /*
             * GENEL HATA
             */
            uiState.generalError?.let { error ->

                Spacer(
                    modifier = Modifier.height(10.dp)
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
                modifier = Modifier.height(22.dp)
            )

            /*
             * KAYIT OL
             */
            AuthPrimaryButton(
                text = "Hesap Oluştur",
                isLoading = uiState.isLoading,
                enabled = true,
                onClick = viewModel::register
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
             * LOGIN
             */
            AuthSecondaryButton(
                text = "Zaten Hesabım Var",
                enabled = !uiState.isLoading,
                onClick = onNavigateToLogin
            )

            Spacer(
                modifier = Modifier.height(24.dp)
            )

            /*
             * ALT BİLGİ
             */
            Text(
                text =
                    "Hesap oluşturarak Kullanım Koşullarını kabul etmiş olursunuz.",
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