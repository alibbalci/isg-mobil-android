package com.alibbalci.isgmobil.presentation.observation.detail.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.alibbalci.isgmobil.domain.model.Observation
import com.alibbalci.isgmobil.ui.theme.Navy

@Composable
fun ObservationRiskCard(
    observation: Observation
) {

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(18.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        )
    ) {

        Column(
            modifier = Modifier.padding(18.dp)
        ) {

            Text(
                text = "Risk Değerlendirmesi",
                style = MaterialTheme.typography.titleMedium,
                color = Navy,
                fontWeight = FontWeight.Bold
            )

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement =
                    Arrangement.SpaceBetween
            ) {

                RiskValue(
                    title = "Olasılık",
                    value = observation.probability
                        ?.toString()
                        ?: "-"
                )

                RiskValue(
                    title = "Şiddet",
                    value = observation.severity
                        ?.toString()
                        ?: "-"
                )

                RiskValue(
                    title = "Risk Skoru",
                    value = observation.riskScore
                        ?.toString()
                        ?: "-"
                )
            }

            /*
             * Önlem sonrası veri varsa
             * ikinci bölüm gösteriyoruz.
             */
            if (
                observation.postProbability != null ||
                observation.postSeverity != null ||
                observation.residualRiskScore != null
            ) {

                Spacer(
                    modifier = Modifier.height(22.dp)
                )

                Text(
                    text = "Önlem Sonrası",
                    style = MaterialTheme.typography.labelLarge,
                    color = Navy,
                    fontWeight = FontWeight.SemiBold
                )

                Spacer(
                    modifier = Modifier.height(12.dp)
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement =
                        Arrangement.SpaceBetween
                ) {

                    RiskValue(
                        title = "Olasılık",
                        value =
                            observation.postProbability
                                ?.toString()
                                ?: "-"
                    )

                    RiskValue(
                        title = "Şiddet",
                        value =
                            observation.postSeverity
                                ?.toString()
                                ?: "-"
                    )

                    RiskValue(
                        title = "Kalan Risk",
                        value =
                            observation.residualRiskScore
                                ?.toString()
                                ?: "-"
                    )
                }
            }
        }
    }
}

@Composable
private fun RiskValue(
    title: String,
    value: String
) {

    Column {

        Text(
            text = title,
            style = MaterialTheme.typography.bodySmall,
            color = Navy.copy(alpha = 0.6f)
        )

        Spacer(
            modifier = Modifier.height(4.dp)
        )

        Text(
            text = value,
            style = MaterialTheme.typography.titleMedium,
            color = Navy,
            fontWeight = FontWeight.Bold
        )
    }
}