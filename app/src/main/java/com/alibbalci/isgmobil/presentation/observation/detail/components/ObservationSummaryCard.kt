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
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.alibbalci.isgmobil.domain.model.Observation
import com.alibbalci.isgmobil.ui.theme.Navy
import com.alibbalci.isgmobil.ui.theme.Orange

@Composable
fun ObservationSummaryCard(
    observation: Observation
) {

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(18.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 3.dp
        )
    ) {

        Column(
            modifier = Modifier.padding(18.dp)
        ) {

            Text(
                text = observation.selectedRiskName
                    ?: "Risk bilgisi bulunamadı",
                style = MaterialTheme.typography.titleLarge,
                color = Navy,
                fontWeight = FontWeight.Bold
            )

            Spacer(
                modifier = Modifier.height(12.dp)
            )

            Row(
                horizontalArrangement =
                    Arrangement.spacedBy(8.dp)
            ) {

                /*
                 * Risk skoru
                 */
                Surface(
                    shape = RoundedCornerShape(20.dp),
                    color = Orange.copy(alpha = 0.12f)
                ) {

                    Text(
                        text = "Risk: ${
                            observation.riskScore ?: "-"
                        }",
                        color = Orange,
                        style = MaterialTheme.typography.labelMedium,
                        modifier = Modifier.padding(
                            horizontal = 10.dp,
                            vertical = 6.dp
                        )
                    )
                }

                /*
                 * Durum
                 */
                Surface(
                    shape = RoundedCornerShape(20.dp),
                    color = Navy.copy(alpha = 0.08f)
                ) {

                    Text(
                        text = observation.status ?: "-",
                        color = Navy,
                        style = MaterialTheme.typography.labelMedium,
                        modifier = Modifier.padding(
                            horizontal = 10.dp,
                            vertical = 6.dp
                        )
                    )
                }
            }

            Spacer(
                modifier = Modifier.height(18.dp)
            )

            SummaryInfoRow(
                label = "Firma",
                value = observation.companyName ?: "-"
            )

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            SummaryInfoRow(
                label = "Tarih",
                value = observation.createdAt ?: "-"
            )

            if (observation.responsiblePerson != null) {

                Spacer(
                    modifier = Modifier.height(8.dp)
                )

                SummaryInfoRow(
                    label = "Sorumlu",
                    value = observation.responsiblePerson
                )
            }
        }
    }
}

@Composable
private fun SummaryInfoRow(
    label: String,
    value: String
) {

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement =
            Arrangement.SpaceBetween
    ) {

        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            color = Navy.copy(alpha = 0.65f)
        )

        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium,
            color = Navy,
            fontWeight = FontWeight.Medium
        )
    }
}