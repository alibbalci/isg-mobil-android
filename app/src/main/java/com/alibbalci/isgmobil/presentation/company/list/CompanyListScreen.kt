import com.alibbalci.isgmobil.presentation.company.list.CompanyListViewModel


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import com.alibbalci.isgmobil.domain.model.Company

@Composable
fun CompanyListScreen(
    viewModel: CompanyListViewModel,
    onCompanyClick: (Long) -> Unit,
    onCreateCompany: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    when {
        uiState.isLoading -> {
            LoadingContent()
        }

        uiState.errorMessage != null -> {
            ErrorContent(
                message = uiState.errorMessage ?: "Bir hata oluştu.",
                onRetry = viewModel::loadCompanies
            )
        }

        else -> {
            CompanyListContent(
                companies = uiState.companies,
                onCompanyClick = { company ->
                    onCompanyClick(company.id)
                },
                onCreateCompany = onCreateCompany
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
    onRetry: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = message)

        Button(
            onClick = onRetry,
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text(text = "Tekrar Dene")
        }
    }
}

@Composable
private fun CompanyListContent(
    companies: List<Company>,
    onCompanyClick: (Company) -> Unit,
    onCreateCompany: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        Button(
            onClick = onCreateCompany,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(text = "Yeni Şirket Oluştur")
        }

        if (companies.isEmpty()) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = "Henüz kayıtlı şirket bulunmuyor.")
            }

        } else {

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                items(
                    items = companies,
                    key = { company -> company.id }
                ) { company ->

                    CompanyItem(
                        company = company,
                        onClick = {
                            onCompanyClick(company)
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun CompanyItem(
    company: Company,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
    ) {

        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            Text(text = company.name)

            company.address?.let { address ->
                Text(text = address)
            }

            company.hazardClass?.let { hazardClass ->
                Text(text = "Tehlike sınıfı: $hazardClass")
            }
        }
    }
}

