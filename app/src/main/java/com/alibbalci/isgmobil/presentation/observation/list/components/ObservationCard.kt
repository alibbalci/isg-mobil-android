package com.alibbalci.isgmobil.presentation.observation.list.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.alibbalci.isgmobil.domain.model.Observation
import com.alibbalci.isgmobil.ui.theme.Navy
import com.alibbalci.isgmobil.ui.theme.Orange

@Composable
fun ObservationCard(
    observation: Observation,
    onClick: () -> Unit
) {

    Card(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            /*
             * GÖZLEM FOTOĞRAFI
             */
            AsyncImage(
                model = observation.photoUrl,
                contentDescription = "Gözlem fotoğrafı",
                modifier = Modifier
                    .size(58.dp)
                    .clip(
                        RoundedCornerShape(12.dp)
                    ),
                contentScale = ContentScale.Crop
            )

            Spacer(
                modifier = Modifier.width(12.dp)
            )

            /*
             * ANA BİLGİLER
             */
            Column(
                modifier = Modifier.weight(1f)
            ) {

                /*
                 * FİRMA ADI
                 */
                Text(
                    text = observation.companyName
                        ?: "Firma bilgisi yok",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    color = Navy,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(
                    modifier = Modifier.height(4.dp)
                )

                /*
                 * RİSK ADI
                 *
                 * İstediğin gibi firma adının
                 * hemen altında gösteriyoruz.
                 */
                Text(
                    text = observation.selectedRiskName
                        ?: "Risk bilgisi bulunamadı",
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Medium,
                    color = Navy,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(
                    modifier = Modifier.height(9.dp)
                )

                /*
                 * RİSK SKORU + DURUM
                 */
                Row(
                    horizontalArrangement =
                        Arrangement.spacedBy(7.dp),
                    verticalAlignment =
                        Alignment.CenterVertically
                ) {

                    observation.riskScore?.let { riskScore ->

                        Surface(
                            shape = RoundedCornerShape(20.dp),
                            color = Orange.copy(
                                alpha = 0.12f
                            )
                        ) {

                            Text(
                                text = "Risk: $riskScore",
                                color = Orange,
                                style =
                                    MaterialTheme
                                        .typography
                                        .labelMedium,
                                modifier = Modifier.padding(
                                    horizontal = 9.dp,
                                    vertical = 5.dp
                                )
                            )
                        }
                    }

                    observation.status?.let { status ->

                        Surface(
                            shape = RoundedCornerShape(20.dp),
                            color = Navy.copy(
                                alpha = 0.08f
                            )
                        ) {

                            Text(
                                text = status,
                                color = Navy,
                                style =
                                    MaterialTheme
                                        .typography
                                        .labelMedium,
                                modifier = Modifier.padding(
                                    horizontal = 9.dp,
                                    vertical = 5.dp
                                )
                            )
                        }
                    }
                }

                /*
                 * TARİH
                 */
                observation.createdAt?.let { createdAt ->

                    Spacer(
                        modifier = Modifier.height(7.dp)
                    )

                    Text(
                        text = formatObservationDate(
                            createdAt
                        ),
                        style =
                            MaterialTheme
                                .typography
                                .bodySmall,
                        color = Navy.copy(
                            alpha = 0.55f
                        )
                    )
                }
            }

            /*
             * DETAIL OKU
             */
            Icon(
                imageVector =
                    Icons.Default.KeyboardArrowRight,
                contentDescription =
                    "Gözlem detayına git",
                tint = Navy.copy(alpha = 0.35f)
            )
        }
    }
}

/*
 * Backend:
 *
 * 2026-08-13T13:25:08.663823
 *
 * döndürüyor.
 *
 * Listede saniye/milisaniye göstermeye
 * gerek olmadığı için:
 *
 * 13.08.2026
 *
 * haline getiriyoruz.
 */
private fun formatObservationDate(
    date: String
): String {

    return try {

        val datePart =
            date.substringBefore("T")

        val parts =
            datePart.split("-")

        if (parts.size == 3) {

            "${parts[2]}.${parts[1]}.${parts[0]}"

        } else {

            date
        }

    } catch (exception: Exception) {

        date
    }
}