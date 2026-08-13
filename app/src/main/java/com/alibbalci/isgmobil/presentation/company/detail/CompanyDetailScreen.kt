package com.alibbalci.isgmobil.presentation.company.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.alibbalci.isgmobil.domain.model.Company
import com.alibbalci.isgmobil.ui.theme.AppBackground
import com.alibbalci.isgmobil.ui.theme.BorderLight
import com.alibbalci.isgmobil.ui.theme.Navy
import com.alibbalci.isgmobil.ui.theme.TextSecondary

@Composable
fun CompanyDetailScreen(viewModel: CompanyDetailViewModel, onBack: () -> Unit) {
    val uiState by viewModel.uiState.collectAsState()
    when {
        uiState.isLoading -> StateContainer { CircularProgressIndicator() }
        uiState.errorMessage != null -> StateContainer {
            Text(uiState.errorMessage ?: "Bir hata oluştu.", color = MaterialTheme.colorScheme.error)
            Spacer(Modifier.height(16.dp))
            Button(onClick = viewModel::loadCompany, modifier = Modifier.fillMaxWidth()) { Text("Tekrar Dene") }
            Spacer(Modifier.height(10.dp))
            OutlinedButton(onClick = onBack, modifier = Modifier.fillMaxWidth()) { Text("Geri Dön") }
        }
        uiState.company != null -> CompanyDetailContent(uiState.company!!, onBack)
    }
}

@Composable
private fun StateContainer(content: @Composable () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize().background(AppBackground).padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Column(modifier = Modifier.fillMaxWidth().widthIn(max = 520.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            content()
        }
    }
}

@Composable
private fun CompanyDetailContent(company: Company, onBack: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize().background(AppBackground)) {
        Column(modifier = Modifier.fillMaxWidth().background(Navy).padding(20.dp)) {
            Text("Şirket Detayı", color = Color.White, style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
            Spacer(Modifier.height(5.dp))
            Text(company.name, color = Color.White.copy(alpha = 0.7f), style = MaterialTheme.typography.bodyMedium)
        }

        Column(
            modifier = Modifier.fillMaxWidth().widthIn(max = 720.dp).align(Alignment.CenterHorizontally)
                .verticalScroll(rememberScrollState()).padding(18.dp)
        ) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                border = androidx.compose.foundation.BorderStroke(1.dp, BorderLight)
            ) {
                Column(Modifier.padding(20.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    Text(company.name, color = Navy, style = MaterialTheme.typography.titleLarge)
                    DetailRow("Adres", company.address)
                    DetailRow("Tehlike sınıfı", company.hazardClass)
                    DetailRow("Telefon", company.phone)
                    DetailRow("İşyeri hekimi", company.occupationalPhysician)
                }
            }
            Spacer(Modifier.height(20.dp))
            OutlinedButton(onClick = onBack, modifier = Modifier.fillMaxWidth().height(52.dp), shape = RoundedCornerShape(14.dp)) {
                Text("Şirketlere Dön")
            }
            Spacer(Modifier.height(24.dp))
        }
    }
}

@Composable
private fun DetailRow(label: String, value: String?) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
        Text(label, color = TextSecondary, style = MaterialTheme.typography.bodyMedium, modifier = Modifier.weight(0.42f))
        Text(value?.takeIf { it.isNotBlank() } ?: "Belirtilmemiş", color = Navy, style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Medium, modifier = Modifier.weight(0.58f))
    }
}
