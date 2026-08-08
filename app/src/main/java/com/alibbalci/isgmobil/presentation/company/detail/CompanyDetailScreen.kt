
package com.alibbalci.isgmobil.presentation.company.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CompanyDetailScreen(
    viewModel: CompanyDetailViewModel,
    onBack: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    when {
        uiState.isLoading -> {
            LoadingContent()
        }

        uiState.errorMessage != null -> {
            ErrorContent(
                message = uiState.errorMessage ?: "Bir hata oluştu.",
                onRetry = viewModel::loadCompany,
                onBack = onBack
            )
        }

        uiState.company != null -> {
            CompanyDetailContent(
                company = uiState.company!!,
                onBack = onBack
            )
        }
    }
}

@Composable
private fun LoadingContent() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
private fun ErrorContent(
    message: String,
    onRetry: () -> Unit,
    onBack: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = message)

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = onRetry,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Tekrar Dene")
        }

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = onBack,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Geri Dön")
        }
    }
}

@Composable
private fun CompanyDetailContent(
    company: com.alibbalci.isgmobil.domain.model.Company,
    onBack: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {

        Text(text = "Şirket Detayı")

        Spacer(modifier = Modifier.height(24.dp))

        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(20.dp)
            ) {

                Text(text = company.name)

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "Adres: ${company.address ?: "Belirtilmemiş"}"
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Tehlike sınıfı: ${company.hazardClass ?: "Belirtilmemiş"}"
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Telefon: ${company.phone ?: "Belirtilmemiş"}"
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "İşyeri hekimi: ${company.occupationalPhysician ?: "Belirtilmemiş"}"
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = onBack,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Geri Dön")
        }
    }
}

