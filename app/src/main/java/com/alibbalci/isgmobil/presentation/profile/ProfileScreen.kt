package com.alibbalci.isgmobil.presentation.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Badge
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.alibbalci.isgmobil.presentation.profile.components.ProfileActionItem
import com.alibbalci.isgmobil.presentation.profile.components.ProfileHeader
import com.alibbalci.isgmobil.presentation.profile.components.ProfileInfoCard
import com.alibbalci.isgmobil.ui.theme.Orange
import com.alibbalci.isgmobil.ui.theme.AppBackground
import com.alibbalci.isgmobil.ui.theme.RiskRed

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel,
    onLogout: () -> Unit
) {

    val uiState by
    viewModel.uiState.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppBackground)
            .verticalScroll(
                rememberScrollState()
            )
    ) {

        /*
         * ÜST PROFİL ALANI
         */
        ProfileHeader(
            fullName = uiState.fullName,
            role = uiState.role
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .widthIn(max = 720.dp)
                .align(Alignment.CenterHorizontally)
                .padding(
                    horizontal = 20.dp,
                    vertical = 22.dp
                )
        ) {

            /*
             * LOADING
             */
            if (uiState.isLoading) {

                CircularProgressIndicator(
                    color = Orange,
                    modifier = Modifier
                        .align(
                            Alignment.CenterHorizontally
                        )
                )

                Spacer(
                    modifier = Modifier.height(20.dp)
                )
            }

            /*
             * HATA
             */
            uiState.errorMessage?.let { error ->

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

                Spacer(
                    modifier = Modifier.height(16.dp)
                )
            }

            /*
             * HESAP BİLGİLERİ
             */
            Text(
                text = "Hesap Bilgileri",
                style =
                    MaterialTheme
                        .typography
                        .titleMedium
            )

            Spacer(
                modifier = Modifier.height(12.dp)
            )

            ProfileInfoCard(
                title = "E-posta",
                value = uiState.email,
                icon = Icons.Default.Email
            )

            Spacer(
                modifier = Modifier.height(10.dp)
            )

            ProfileInfoCard(
                title = "Rol",
                value = formatRole(
                    uiState.role
                ),
                icon = Icons.Default.Badge
            )

            Spacer(
                modifier = Modifier.height(28.dp)
            )

            /*
             * AYARLAR
             */
            Text(
                text = "Ayarlar",
                style =
                    MaterialTheme
                        .typography
                        .titleMedium
            )

            Spacer(
                modifier = Modifier.height(12.dp)
            )

            ProfileActionItem(
                title = "Bildirimler",
                subtitle = "Bildirim tercihlerini yönet",
                icon = Icons.Default.Notifications,
                onClick = {
                    // TODO Bildirim ayarları ekranı
                }
            )

            Spacer(
                modifier = Modifier.height(10.dp)
            )

            ProfileActionItem(
                title = "Şifre Değiştir",
                subtitle = "Hesap şifrenizi güncelleyin",
                icon = Icons.Default.Lock,
                onClick = {
                    // TODO Şifre değiştirme ekranı
                }
            )

            Spacer(
                modifier = Modifier.height(10.dp)
            )

            ProfileActionItem(
                title = "Uygulama Ayarları",
                subtitle = "Uygulama tercihlerini düzenle",
                icon = Icons.Default.Settings,
                onClick = {
                    // TODO Ayarlar ekranı
                }
            )

            Spacer(
                modifier = Modifier.height(30.dp)
            )

            /*
             * ÇIKIŞ
             */
            Button(
                onClick = onLogout,
                enabled = !uiState.isLoading,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp),
                shape = RoundedCornerShape(14.dp),
                colors =
                    ButtonDefaults.buttonColors(
                        containerColor =
                            RiskRed,
                        contentColor =
                            Color.White,
                        disabledContainerColor =
                            RiskRed
                                .copy(alpha = 0.45f)
                    )
            ) {

                Text(
                    text = "Çıkış Yap",
                    style =
                        MaterialTheme
                            .typography
                            .labelLarge
                )
            }

            Spacer(
                modifier = Modifier.height(28.dp)
            )
        }
    }
}

private fun formatRole(
    role: String
): String {

    return when (
        role.uppercase()
    ) {

        "ADMIN" ->
            "Yönetici"

        "ISG_EXPERT",
        "OHS_EXPERT" ->
            "İSG Uzmanı"

        "USER" ->
            "Kullanıcı"

        else ->
            role
                .replace(
                    "_",
                    " "
                )
                .lowercase()
                .replaceFirstChar {
                    it.uppercase()
                }
    }
}
