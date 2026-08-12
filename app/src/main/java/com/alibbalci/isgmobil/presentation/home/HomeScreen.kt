package com.alibbalci.isgmobil.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Business
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.alibbalci.isgmobil.presentation.home.components.HomeHeader
import com.alibbalci.isgmobil.presentation.home.components.QuickActionCard
import com.alibbalci.isgmobil.presentation.home.components.RecentObservationCard
import com.alibbalci.isgmobil.presentation.home.components.StatCard
import com.alibbalci.isgmobil.ui.theme.Navy
import com.alibbalci.isgmobil.ui.theme.Orange
import com.alibbalci.isgmobil.ui.theme.TextSecondary

@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    onNavigateToCompanies: () -> Unit,
    onNavigateToObservationCreate: () -> Unit,
    onNavigateToObservations: () -> Unit
) {

    val uiState by
    viewModel.uiState.collectAsStateWithLifecycle()

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
        HomeHeader(
            userName = uiState.userName,
            role = formatRole(
                uiState.userRole
            )
        )

        /*
         * ANA İÇERİK
         */
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 16.dp
                )
        ) {

            /*
             * LOADING
             */
            if (uiState.isLoading) {

                Spacer(
                    modifier = Modifier.height(28.dp)
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement =
                        Arrangement.Center,
                    verticalAlignment =
                        Alignment.CenterVertically
                ) {

                    CircularProgressIndicator(
                        color = Orange
                    )
                }

                Spacer(
                    modifier = Modifier.height(28.dp)
                )
            }

            /*
             * HATA
             */
            uiState.errorMessage?.let { errorMessage ->

                Spacer(
                    modifier = Modifier.height(16.dp)
                )

                Text(
                    text = errorMessage,
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
             * İSTATİSTİKLER
             */
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement =
                    Arrangement.spacedBy(10.dp)
            ) {

                Column(
                    modifier = Modifier.weight(1f)
                ) {

                    StatCard(
                        value =
                            uiState.totalObservations
                                .toString(),
                        title = "Toplam Gözlem",
                        backgroundColor = Navy,
                        icon =
                            Icons.Default.Description
                    )

                    Spacer(
                        modifier =
                            Modifier.height(10.dp)
                    )

                    StatCard(
                        value =
                            uiState.pendingCount
                                .toString(),
                        title = "Beklemede",
                        backgroundColor =
                            Color(0xFFFF7A30),
                        icon =
                            Icons.Default.Schedule
                    )
                }

                Column(
                    modifier = Modifier.weight(1f)
                ) {

                    StatCard(
                        value =
                            uiState.highRiskCount
                                .toString(),
                        title = "Yüksek Riskli",
                        backgroundColor =
                            Color(0xFFEF2D35),
                        icon =
                            Icons.Default.Warning
                    )

                    Spacer(
                        modifier =
                            Modifier.height(10.dp)
                    )

                    StatCard(
                        value =
                            uiState.resolvedCount
                                .toString(),
                        title = "Çözüldü",
                        backgroundColor =
                            Color(0xFF1EB855),
                        icon =
                            Icons.Default.CheckCircle
                    )
                }
            }

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            /*
             * YENİ GÖZLEM
             */
            Button(
                onClick =
                    onNavigateToObservationCreate,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp),
                shape =
                    RoundedCornerShape(
                        14.dp
                    ),
                colors =
                    ButtonDefaults
                        .buttonColors(
                            containerColor =
                                Orange,
                            contentColor =
                                Color.White
                        )
            ) {

                Text(
                    text =
                        "＋  Yeni Gözlem Oluştur",
                    style =
                        MaterialTheme
                            .typography
                            .labelLarge,
                    fontWeight =
                        FontWeight.SemiBold
                )
            }

            Spacer(
                modifier = Modifier.height(20.dp)
            )

            /*
             * HIZLI MENÜ
             */
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement =
                    Arrangement.spacedBy(10.dp)
            ) {

                QuickActionCard(
                    title = "Şirketler",
                    icon =
                        Icons.Default.Business,
                    modifier =
                        Modifier.weight(1f),
                    onClick =
                        onNavigateToCompanies
                )

                QuickActionCard(
                    title = "Tüm Gözlemler",
                    icon =
                        Icons.Default.List,
                    modifier =
                        Modifier.weight(1f),
                    onClick =
                        onNavigateToObservations
                )
            }

            Spacer(
                modifier = Modifier.height(24.dp)
            )

            /*
             * SON GÖZLEMLER
             */
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement =
                    Arrangement.SpaceBetween,
                verticalAlignment =
                    Alignment.CenterVertically
            ) {

                Text(
                    text = "Son Gözlemler",
                    color = Navy,
                    style =
                        MaterialTheme
                            .typography
                            .titleMedium,
                    fontWeight =
                        FontWeight.Bold
                )

                TextButton(
                    onClick =
                        onNavigateToObservations
                ) {

                    Text(
                        text = "Tümünü Gör",
                        color = Orange
                    )
                }
            }

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            /*
             * GERÇEK SON GÖZLEMLER
             */
            if (
                !uiState.isLoading &&
                uiState.recentObservations.isEmpty()
            ) {

                Text(
                    text =
                        "Henüz gözlem bulunmuyor.",
                    color = TextSecondary,
                    style =
                        MaterialTheme
                            .typography
                            .bodyMedium
                )

            } else {

                uiState.recentObservations
                    .forEachIndexed { index, observation ->

                        RecentObservationCard(
                            title =
                                observation.selectedRiskName
                                    ?: observation.description
                                    ?: observation.aiDescription
                                    ?: "Gözlem",

                            location =
                                observation.companyName
                                    ?: "Şirket bilgisi yok",

                            riskText =
                                formatRiskLevel(
                                    observation.riskLevel
                                ),

                            statusText =
                                formatObservationStatus(
                                    observation.status
                                )
                        )

                        if (
                            index !=
                            uiState.recentObservations.lastIndex
                        ) {

                            Spacer(
                                modifier =
                                    Modifier.height(
                                        12.dp
                                    )
                            )
                        }
                    }
            }

            Spacer(
                modifier = Modifier.height(30.dp)
            )
        }
    }
}

/*
 * Backend role değerini
 * kullanıcı dostu metne çevirir.
 */
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

/*
 * Risk enum değerini
 * kullanıcı dostu gösterir.
 */
private fun formatRiskLevel(
    riskLevel: String?
): String {

    return when (
        riskLevel?.uppercase()
    ) {

        "LOW" ->
            "Düşük"

        "MEDIUM" ->
            "Orta"

        "HIGH" ->
            "Yüksek"

        "CRITICAL" ->
            "Kritik"

        else ->
            "Belirsiz"
    }
}

/*
 * Status enum değerini
 * kullanıcı dostu gösterir.
 */
private fun formatObservationStatus(
    status: String?
): String {

    return when (
        status?.uppercase()
    ) {

        "PENDING_AI" ->
            "AI Bekleniyor"

        "AI_ANALYZED" ->
            "Analiz Edildi"

        "CONFIRMED" ->
            "Onaylandı"

        "REVIEWED" ->
            "İncelendi"

        "APPROVED" ->
            "Çözüldü"

        "REJECTED" ->
            "Reddedildi"

        else ->
            "Bilinmiyor"
    }
}