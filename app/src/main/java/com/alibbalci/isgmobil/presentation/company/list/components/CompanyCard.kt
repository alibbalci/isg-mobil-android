package com.alibbalci.isgmobil.presentation.company.list.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Business
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.alibbalci.isgmobil.domain.model.Company
import com.alibbalci.isgmobil.ui.theme.Navy

@Composable
fun CompanyCard(
    company: Company,
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
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            /*
             * ŞİRKET İKONU
             */
            Surface(
                modifier = Modifier.size(44.dp),
                shape = RoundedCornerShape(12.dp),
                color = Navy
            ) {

                Icon(
                    imageVector = Icons.Default.Business,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.padding(10.dp)
                )
            }

            Spacer(
                modifier = Modifier.width(12.dp)
            )

            /*
             * ŞİRKET BİLGİLERİ
             */
            Column(
                modifier = Modifier.weight(1f)
            ) {

                Text(
                    text = company.name,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = Navy,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                company.hazardClass?.let { hazardClass ->

                    Text(
                        text = hazardClass,
                        style = MaterialTheme.typography.bodySmall,
                        color = Navy.copy(alpha = 0.6f),
                        modifier = Modifier.padding(
                            top = 3.dp
                        )
                    )
                }

                company.address?.let { address ->

                    Text(
                        text = address,
                        style = MaterialTheme.typography.bodySmall,
                        color = Navy.copy(alpha = 0.55f),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.padding(
                            top = 8.dp
                        )
                    )
                }
            }

            Icon(
                imageVector =
                    Icons.Default.KeyboardArrowRight,
                contentDescription =
                    "Şirket detayına git",
                tint =
                    Navy.copy(alpha = 0.3f)
            )
        }
    }
}