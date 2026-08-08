package com.alibbalci.isgmobil.presentation.company.create

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CompanyCreateScreen(
    viewModel: CompanyCreateViewModel,
    onCompanyCreated: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(uiState.isCreated) {
        if (uiState.isCreated) {
            viewModel.consumeCreatedEvent()
            onCompanyCreated()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center
    ) {

        Text(text = "Yeni Şirket Oluştur")

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = uiState.name,
            onValueChange = viewModel::onNameChange,
            label = {
                Text("Şirket Adı *")
            },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = uiState.address,
            onValueChange = viewModel::onAddressChange,
            label = {
                Text("Adres")
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = uiState.hazardClass,
            onValueChange = viewModel::onHazardClassChange,
            label = {
                Text("Tehlike Sınıfı")
            },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = uiState.phone,
            onValueChange = viewModel::onPhoneChange,
            label = {
                Text("Telefon")
            },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = uiState.occupationalPhysician,
            onValueChange = viewModel::onOccupationalPhysicianChange,
            label = {
                Text("İşyeri Hekimi")
            },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        uiState.errorMessage?.let { errorMessage ->

            Spacer(modifier = Modifier.height(12.dp))

            Text(text = errorMessage)
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = viewModel::createCompany,
            enabled = !uiState.isLoading,
            modifier = Modifier.fillMaxWidth()
        ) {

            if (uiState.isLoading) {
                CircularProgressIndicator()
            } else {
                Text("Şirket Oluştur")
            }
        }
    }
}