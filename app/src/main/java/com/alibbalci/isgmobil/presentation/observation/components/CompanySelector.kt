package com.alibbalci.isgmobil.presentation.observation.create.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.alibbalci.isgmobil.domain.model.Company
import com.alibbalci.isgmobil.ui.theme.BorderLight
import com.alibbalci.isgmobil.ui.theme.InputBackground
import com.alibbalci.isgmobil.ui.theme.Navy
import com.alibbalci.isgmobil.ui.theme.Orange
import com.alibbalci.isgmobil.ui.theme.TextSecondary

@Composable
fun CompanySelector(
    companies: List<Company>,
    selectedCompany: Company?,
    isLoading: Boolean,
    expanded: Boolean,
    onExpandedChange: (Boolean) -> Unit,
    onCompanySelected: (Company) -> Unit
) {

    var searchText by remember {
        mutableStateOf("")
    }

    val filteredCompanies =
        companies.filter { company ->
            company.name.contains(
                searchText,
                ignoreCase = true
            )
        }

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {

        Text(
            text = "Şirket",
            color = Navy,
            style = MaterialTheme.typography.titleSmall
        )

        Spacer(
            modifier = Modifier.height(10.dp)
        )

        if (isLoading) {

            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp),
                shape = RoundedCornerShape(12.dp),
                color = InputBackground,
                border = BorderStroke(
                    width = 1.dp,
                    color = BorderLight
                )
            ) {

                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {

                    CircularProgressIndicator(
                        color = Orange
                    )
                }
            }

        } else {

            Box(
                modifier = Modifier.fillMaxWidth()
            ) {

                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            onExpandedChange(true)
                        },
                    shape = RoundedCornerShape(12.dp),
                    color = InputBackground,
                    border = BorderStroke(
                        width = 1.dp,
                        color = if (expanded) {
                            Orange
                        } else {
                            BorderLight
                        }
                    )
                ) {

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                horizontal = 16.dp,
                                vertical = 15.dp
                            )
                    ) {

                        Text(
                            text = selectedCompany?.name
                                ?: "Şirket seçin",
                            color = if (selectedCompany != null) {
                                Navy
                            } else {
                                TextSecondary
                            },
                            style = MaterialTheme.typography.bodyLarge
                        )

                        Text(
                            text = if (expanded) "▲" else "▼",
                            color = TextSecondary,
                            style = MaterialTheme.typography.bodySmall,
                            modifier = Modifier.align(
                                Alignment.CenterEnd
                            )
                        )
                    }
                }

                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = {
                        onExpandedChange(false)
                        searchText = ""
                    },
                    modifier = Modifier
                        .fillMaxWidth(0.92f)
                ) {

                    OutlinedTextField(
                        value = searchText,
                        onValueChange = {
                            searchText = it
                        },
                        placeholder = {
                            Text(
                                text = "Şirket ara..."
                            )
                        },
                        singleLine = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                horizontal = 8.dp,
                                vertical = 6.dp
                            )
                    )

                    if (filteredCompanies.isEmpty()) {

                        DropdownMenuItem(
                            text = {
                                Text(
                                    text = if (searchText.isBlank()) {
                                        "Kayıtlı şirket bulunamadı."
                                    } else {
                                        "\"$searchText\" için şirket bulunamadı."
                                    }
                                )
                            },
                            onClick = {},
                            enabled = false
                        )

                    } else {

                        filteredCompanies.forEach { company ->

                            DropdownMenuItem(
                                text = {

                                    Column {

                                        Text(
                                            text = company.name,
                                            color = Navy,
                                            style = MaterialTheme.typography.bodyLarge
                                        )

                                        company.hazardClass?.let { hazardClass ->

                                            Spacer(
                                                modifier = Modifier.height(2.dp)
                                            )

                                            Text(
                                                text = "Tehlike sınıfı: $hazardClass",
                                                color = TextSecondary,
                                                style = MaterialTheme.typography.bodySmall
                                            )
                                        }
                                    }
                                },

                                onClick = {

                                    onCompanySelected(
                                        company
                                    )

                                    searchText = ""

                                    onExpandedChange(
                                        false
                                    )
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}