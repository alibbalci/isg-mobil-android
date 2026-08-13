package com.alibbalci.isgmobil.presentation.observation.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.alibbalci.isgmobil.presentation.observation.list.components.ObservationCard
import com.alibbalci.isgmobil.ui.theme.AppBackground
import com.alibbalci.isgmobil.ui.theme.Navy

@Composable
fun ObservationListScreen(
    viewModel: ObservationListViewModel = hiltViewModel(),
    onObservationClick: (Long) -> Unit = {}
) {

    val uiState by
    viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                AppBackground
            )
    ) {

        /*
         * HEADER
         */
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Navy
                )
                .padding(
                    horizontal = 20.dp,
                    vertical = 18.dp
                )
        ) {

            Text(
                text = "Gözlemler",
                color = Color.White,
                style =
                    MaterialTheme
                        .typography
                        .headlineSmall,
                fontWeight =
                    FontWeight.Bold
            )

            Spacer(
                modifier =
                    Modifier.height(5.dp)
            )

            Text(
                text =
                    "${uiState.observations.size} toplam kayıt",
                color =
                    Color.White.copy(
                        alpha = 0.65f
                    ),
                style =
                    MaterialTheme
                        .typography
                        .bodySmall
            )
        }

        /*
         * LOADING
         */
        when {

            uiState.isLoading -> {

                Box(
                    modifier =
                        Modifier.fillMaxSize(),
                    contentAlignment =
                        Alignment.Center
                ) {

                    CircularProgressIndicator()
                }
            }

            /*
             * ERROR
             */
            uiState.errorMessage != null -> {

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(24.dp),
                    verticalArrangement =
                        Arrangement.Center,
                    horizontalAlignment =
                        Alignment.CenterHorizontally
                ) {

                    Text(
                        text =
                            uiState.errorMessage
                                ?: "Bir hata oluştu."
                    )

                    Spacer(
                        modifier =
                            Modifier.height(16.dp)
                    )

                    Button(
                        onClick = {
                            viewModel
                                .loadObservations()
                        }
                    ) {

                        Text(
                            text = "Tekrar Dene"
                        )
                    }
                }
            }

            /*
             * EMPTY
             */
            uiState.isEmpty -> {

                Box(
                    modifier =
                        Modifier.fillMaxSize(),
                    contentAlignment =
                        Alignment.Center
                ) {

                    Text(
                        text =
                            "Henüz oluşturulmuş bir gözlem yok.",
                        color = Navy
                    )
                }
            }

            /*
             * LISTE
             */
            else -> {

                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .widthIn(max = 720.dp)
                        .align(Alignment.CenterHorizontally),
                    contentPadding =
                        PaddingValues(
                            horizontal = 14.dp,
                            vertical = 16.dp
                        ),
                    verticalArrangement =
                        Arrangement.spacedBy(
                            10.dp
                        )
                ) {

                    items(
                        items =
                            uiState.observations,
                        key = { observation ->
                            observation.id
                        }
                    ) { observation ->

                        ObservationCard(
                            observation =
                                observation,

                            onClick = {

                                onObservationClick(
                                    observation.id
                                )
                            }
                        )
                    }
                }
            }
        }
    }
}
