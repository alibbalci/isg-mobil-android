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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.alibbalci.isgmobil.presentation.home.components.HomeHeader
import com.alibbalci.isgmobil.presentation.home.components.QuickActionCard
import com.alibbalci.isgmobil.presentation.home.components.RecentObservationCard
import com.alibbalci.isgmobil.presentation.home.components.StatCard
import com.alibbalci.isgmobil.ui.theme.Navy
import com.alibbalci.isgmobil.ui.theme.Orange

@Composable
fun HomeScreen(
    onNavigateToCompanies: () -> Unit,
    onNavigateToObservationCreate: () -> Unit,
    onNavigateToObservations: () -> Unit,
    onLogout: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(
                rememberScrollState()
            )
    ) {

        HomeHeader(
            userName = "Ahmet Yılmaz",
            role = "İSG Uzmanı",
            companyName = "Metalsan A.Ş."
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 16.dp
                )
        ) {

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
                        value = "6",
                        title = "Toplam Gözlem",
                        backgroundColor = Navy,
                        icon = Icons.Default.Description
                    )

                    Spacer(
                        modifier = Modifier.height(10.dp)
                    )

                    StatCard(
                        value = "2",
                        title = "Beklemede",
                        backgroundColor = Color(0xFFFF7A30),
                        icon = Icons.Default.Schedule
                    )
                }

                Column(
                    modifier = Modifier.weight(1f)
                ) {

                    StatCard(
                        value = "3",
                        title = "Yüksek Riskli",
                        backgroundColor = Color(0xFFEF2D35),
                        icon = Icons.Default.Warning
                    )

                    Spacer(
                        modifier = Modifier.height(10.dp)
                    )

                    StatCard(
                        value = "3",
                        title = "Çözüldü",
                        backgroundColor = Color(0xFF1EB855),
                        icon = Icons.Default.CheckCircle
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
                onClick = onNavigateToObservationCreate,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp),
                shape = RoundedCornerShape(14.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Orange,
                    contentColor = Color.White
                )
            ) {

                Text(
                    text = "＋  Yeni Gözlem Oluştur",
                    style =
                        MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Spacer(
                modifier = Modifier.height(20.dp)
            )

            /*
             * HIZLI MENÜLER
             */
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement =
                    Arrangement.spacedBy(10.dp)
            ) {

                QuickActionCard(
                    title = "Şirketler",
                    icon = Icons.Default.Business,
                    modifier = Modifier.weight(1f),
                    onClick = onNavigateToCompanies
                )

                QuickActionCard(
                    title = "Tüm Gözlemler",
                    icon = Icons.Default.List,
                    modifier = Modifier.weight(1f),
                    onClick = onNavigateToObservations
                )
            }

            Spacer(
                modifier = Modifier.height(24.dp)
            )

            /*
             * SON GÖZLEMLER BAŞLIK
             */
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement =
                    Arrangement.SpaceBetween
            ) {

                Text(
                    text = "Son Gözlemler",
                    color = Navy,
                    style =
                        MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )

                TextButton(
                    onClick = onNavigateToObservations
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
             * ŞİMDİLİK ÖRNEK VERİ
             */
            RecentObservationCard(
                title = "Elektrik Panosu Açık",
                location = "Üretim Salonu A",
                riskText = "Yüksek",
                statusText = "Beklemede"
            )

            Spacer(
                modifier = Modifier.height(12.dp)
            )

            RecentObservationCard(
                title = "Koruyucu Ekipman Kullanılmıyor",
                location = "Montaj Alanı",
                riskText = "Yüksek",
                statusText = "Beklemede"
            )

            Spacer(
                modifier = Modifier.height(24.dp)
            )

            /*
             * ŞİMDİLİK ÇIKIŞ
             * Daha sonra Profile ekranına taşıyacağız.
             */
            TextButton(
                onClick = onLogout,
                modifier = Modifier.fillMaxWidth()
            ) {

                Text(
                    text = "Çıkış Yap",
                    color = Color.Gray
                )
            }

            Spacer(
                modifier = Modifier.height(24.dp)
            )
        }
    }
}