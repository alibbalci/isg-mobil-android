package com.alibbalci.isgmobil.presentation.observation.create.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.alibbalci.isgmobil.domain.model.RiskCandidate
import com.alibbalci.isgmobil.ui.theme.BorderLight
import com.alibbalci.isgmobil.ui.theme.CardBackground
import com.alibbalci.isgmobil.ui.theme.Navy
import com.alibbalci.isgmobil.ui.theme.Orange
import com.alibbalci.isgmobil.ui.theme.OrangeSoft
import com.alibbalci.isgmobil.ui.theme.TextSecondary

@Composable
fun RiskCandidateCard(
    index: Int,
    risk: RiskCandidate,
    isSelected: Boolean,
    selectedSuggestion: String?,
    onRiskSelected: () -> Unit,
    onSuggestionSelected: (String) -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onRiskSelected()
            },
        shape = RoundedCornerShape(16.dp),
        color = if (isSelected) {
            OrangeSoft
        } else {
            CardBackground
        },
        border = BorderStroke(
            width = if (isSelected) 1.5.dp else 1.dp,
            color = if (isSelected) {
                Orange
            } else {
                BorderLight
            }
        ),
        shadowElevation = if (isSelected) {
            2.dp
        } else {
            1.dp
        }
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {

            /*
             * RİSK ÜST BÖLÜMÜ
             */
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Top
            ) {

                RadioButton(
                    selected = isSelected,
                    onClick = onRiskSelected,
                    colors = RadioButtonDefaults.colors(
                        selectedColor = Orange,
                        unselectedColor = BorderLight
                    )
                )

                Spacer(
                    modifier = Modifier.width(6.dp)
                )

                Column(
                    modifier = Modifier.weight(1f)
                ) {

                    Text(
                        text = "Risk $index",
                        color = Orange,
                        style = MaterialTheme.typography.labelMedium
                    )

                    Spacer(
                        modifier = Modifier.height(4.dp)
                    )

                    Text(
                        text = risk.name,
                        color = Navy,
                        style = MaterialTheme.typography.titleSmall
                    )

                    Spacer(
                        modifier = Modifier.height(8.dp)
                    )

                    Text(
                        text = "Kod: ${risk.code}",
                        color = TextSecondary,
                        style = MaterialTheme.typography.bodySmall
                    )

                    Spacer(
                        modifier = Modifier.height(4.dp)
                    )

                    Text(
                        text = "Benzerlik skoru: ${
                            String.format(
                                "%.2f",
                                risk.score
                            )
                        }",
                        color = TextSecondary,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }

            /*
             * OLASI ZARAR
             */
            risk.damage?.let { damage ->

                Spacer(
                    modifier = Modifier.height(14.dp)
                )

                Text(
                    text = "Olası Zarar",
                    color = Navy,
                    style = MaterialTheme.typography.labelMedium
                )

                Spacer(
                    modifier = Modifier.height(4.dp)
                )

                Text(
                    text = damage,
                    color = TextSecondary,
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            /*
             * SADECE SEÇİLİ RİSKİN
             * ÖNERİLERİNİ AÇ
             */
            if (isSelected) {

                Spacer(
                    modifier = Modifier.height(18.dp)
                )

                Text(
                    text = "Önerilen Önlemler",
                    color = Navy,
                    style = MaterialTheme.typography.titleSmall
                )

                Spacer(
                    modifier = Modifier.height(10.dp)
                )

                if (risk.suggestions.isEmpty()) {

                    Text(
                        text = "Bu risk için öneri bulunamadı.",
                        color = TextSecondary,
                        style = MaterialTheme.typography.bodyMedium
                    )

                } else {

                    risk.suggestions.forEachIndexed { suggestionIndex, suggestion ->

                        SuggestionItem(
                            suggestion = suggestion,
                            isSelected =
                                selectedSuggestion == suggestion,
                            onClick = {
                                onSuggestionSelected(
                                    suggestion
                                )
                            }
                        )

                        if (
                            suggestionIndex !=
                            risk.suggestions.lastIndex
                        ) {

                            Spacer(
                                modifier = Modifier.height(8.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}