package com.alibbalci.isgmobil.presentation.observation.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.alibbalci.isgmobil.presentation.observation.detail.components.ObservationDetailHeader
import com.alibbalci.isgmobil.presentation.observation.detail.components.ObservationHeroImage
import com.alibbalci.isgmobil.presentation.observation.detail.components.ObservationInfoCard
import com.alibbalci.isgmobil.presentation.observation.detail.components.ObservationRiskCard
import com.alibbalci.isgmobil.presentation.observation.detail.components.ObservationSummaryCard
import com.alibbalci.isgmobil.ui.theme.AppBackground
import com.alibbalci.isgmobil.ui.theme.Navy

@Composable
fun ObservationDetailScreen(
    viewModel: ObservationDetailViewModel,
    onBack: () -> Unit
) {

    val uiState by viewModel.uiState.collectAsState()

    when {

        /*
         * LOADING
         */
        uiState.isLoading -> {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        AppBackground
                    ),
                verticalArrangement =
                    Arrangement.Center,
                horizontalAlignment =
                    Alignment.CenterHorizontally
            ) {

                CircularProgressIndicator()

                Spacer(
                    modifier =
                        Modifier.height(12.dp)
                )

                Text(
                    text =
                        "Gözlem detayı yükleniyor...",
                    style =
                        MaterialTheme
                            .typography
                            .bodyMedium
                )
            }
        }

        /*
         * ERROR
         */
        uiState.errorMessage != null -> {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        AppBackground
                    )
                    .padding(24.dp),
                verticalArrangement =
                    Arrangement.Center,
                horizontalAlignment =
                    Alignment.CenterHorizontally
            ) {

                Text(
                    text =
                        uiState.errorMessage
                            ?: "Gözlem detayı yüklenemedi.",
                    style =
                        MaterialTheme
                            .typography
                            .bodyLarge
                )

                Spacer(
                    modifier =
                        Modifier.height(16.dp)
                )

                Button(
                    onClick = {
                        viewModel.loadObservation()
                    }
                ) {

                    Text(
                        text = "Tekrar Dene"
                    )
                }

                Spacer(
                    modifier =
                        Modifier.height(8.dp)
                )

                Button(
                    onClick = onBack
                ) {

                    Text(
                        text = "Geri Dön"
                    )
                }
            }
        }

        /*
         * SUCCESS
         */
        uiState.observation != null -> {

            val observation =
                uiState.observation

            if (observation != null) {

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            AppBackground
                        )
                ) {

                    /*
                     * LACİVERT HEADER
                     */
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                Navy
                            )
                    ) {

                        ObservationDetailHeader(
                            observationId =
                                observation.id,

                            createdAt =
                                observation.createdAt
                        )
                    }

                    /*
                     * SAYFA İÇERİĞİ
                     */
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .widthIn(max = 760.dp)
                            .align(Alignment.CenterHorizontally)
                            .verticalScroll(
                                rememberScrollState()
                            )
                            .padding(
                                horizontal = 18.dp,
                                vertical = 14.dp
                            )
                    ) {

                        /*
                         * FOTOĞRAF
                         */
                        ObservationHeroImage(
                            photoUrl =
                                observation.photoUrl
                        )

                        if (
                            !observation.photoUrl
                                .isNullOrBlank()
                        ) {

                            Spacer(
                                modifier =
                                    Modifier.height(14.dp)
                            )
                        }

                        /*
                         * ANA ÖZET KARTI
                         */
                        ObservationSummaryCard(
                            observation =
                                observation
                        )

                        Spacer(
                            modifier =
                                Modifier.height(14.dp)
                        )

                        /*
                         * AÇIKLAMA
                         */
                        ObservationInfoCard(
                            title = "Açıklama",
                            content =
                                observation.description
                                    ?: observation.aiDescription
                                    ?: "Açıklama bulunamadı."
                        )

                        /*
                         * OLASI ZARAR
                         */
                        if (
                            !observation.possibleDamage
                                .isNullOrBlank()
                        ) {

                            Spacer(
                                modifier =
                                    Modifier.height(14.dp)
                            )

                            ObservationInfoCard(
                                title =
                                    "Olası Zarar",

                                content =
                                    observation.possibleDamage
                            )
                        }

                        Spacer(
                            modifier =
                                Modifier.height(14.dp)
                        )

                        /*
                         * RİSK DEĞERLENDİRMESİ
                         */
                        ObservationRiskCard(
                            observation =
                                observation
                        )

                        /*
                         * ÖNERİLER
                         */
                        if (
                            !observation.suggestions
                                .isNullOrBlank()
                        ) {

                            Spacer(
                                modifier =
                                    Modifier.height(14.dp)
                            )

                            ObservationInfoCard(
                                title =
                                    "Önerilen Önlemler",

                                content =
                                    observation.suggestions
                            )
                        }

                        /*
                         * SORUMLU KİŞİ
                         */
                        if (
                            !observation.responsiblePerson
                                .isNullOrBlank()
                        ) {

                            Spacer(
                                modifier =
                                    Modifier.height(14.dp)
                            )

                            ObservationInfoCard(
                                title =
                                    "Sorumlu Kişi",

                                content =
                                    observation.responsiblePerson
                            )
                        }

                        /*
                         * DÜZELTME SÜRESİ
                         */
                        observation.dueDays
                            ?.let { days ->

                                Spacer(
                                    modifier =
                                        Modifier.height(
                                            14.dp
                                        )
                                )

                                ObservationInfoCard(
                                    title =
                                        "Düzeltme Süresi",

                                    content =
                                        "$days gün"
                                )
                            }

                        /*
                         * ONAY TARİHİ
                         */
                        if (
                            !observation.confirmedAt
                                .isNullOrBlank()
                        ) {

                            Spacer(
                                modifier =
                                    Modifier.height(14.dp)
                            )

                            ObservationInfoCard(
                                title =
                                    "Onay Tarihi",

                                content =
                                    observation.confirmedAt
                            )
                        }

                        Spacer(
                            modifier =
                                Modifier.height(18.dp)
                        )

                        /*
                         * GERİ DÖN
                         */
                        Button(
                            onClick = onBack,
                            modifier =
                                Modifier.fillMaxWidth()
                        ) {

                            Text(
                                text =
                                    "Gözlemlerime Dön"
                            )
                        }

                        Spacer(
                            modifier =
                                Modifier.height(24.dp)
                        )
                    }
                }
            }
        }

        /*
         * BOŞ STATE
         */
        else -> {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        AppBackground
                    ),
                verticalArrangement =
                    Arrangement.Center,
                horizontalAlignment =
                    Alignment.CenterHorizontally
            ) {

                Text(
                    text =
                        "Gözlem bilgisi bulunamadı."
                )

                Spacer(
                    modifier =
                        Modifier.height(12.dp)
                )

                Button(
                    onClick = onBack
                ) {

                    Text(
                        text = "Geri Dön"
                    )
                }
            }
        }
    }
}
