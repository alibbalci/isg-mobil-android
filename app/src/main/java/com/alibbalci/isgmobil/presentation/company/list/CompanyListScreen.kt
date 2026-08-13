import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.alibbalci.isgmobil.presentation.company.list.CompanyListViewModel
import com.alibbalci.isgmobil.presentation.company.list.components.CompanyCard
import com.alibbalci.isgmobil.ui.theme.AppBackground
import com.alibbalci.isgmobil.ui.theme.Navy
import com.alibbalci.isgmobil.ui.theme.Orange

@Composable
fun CompanyListScreen(
    viewModel: CompanyListViewModel,
    onCompanyClick: (Long) -> Unit,
    onCreateCompany: () -> Unit
) {

    val uiState by
    viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                AppBackground
            )
    ) {

        /*
         * HEADER
         */
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Navy
                )
                .padding(
                    horizontal = 20.dp,
                    vertical = 20.dp
                )
        ) {

            Text(
                text = "Şirketler",
                color = Color.White,
                style =
                    MaterialTheme
                        .typography
                        .headlineSmall,
                fontWeight =
                    FontWeight.Bold
            )

            Spacer(
                modifier =
                    Modifier.height(5.dp)
            )

            Text(
                text =
                    "${uiState.companies.size} şirket kayıtlı",
                color =
                    Color.White.copy(
                        alpha = 0.65f
                    ),
                style =
                    MaterialTheme
                        .typography
                        .bodySmall
            )
        }

        when {

            /*
             * LOADING
             */
            uiState.isLoading -> {

                Box(
                    modifier =
                        Modifier.fillMaxSize(),
                    contentAlignment =
                        Alignment.Center
                ) {

                    CircularProgressIndicator()
                }
            }

            /*
             * ERROR
             */
            uiState.errorMessage != null -> {

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(24.dp),
                    horizontalAlignment =
                        Alignment.CenterHorizontally,
                    verticalArrangement =
                        Arrangement.Center
                ) {

                    Text(
                        text =
                            uiState.errorMessage
                                ?: "Bir hata oluştu."
                    )

                    Spacer(
                        modifier =
                            Modifier.height(16.dp)
                    )

                    Button(
                        onClick =
                            viewModel::loadCompanies
                    ) {

                        Text(
                            text = "Tekrar Dene"
                        )
                    }
                }
            }

            else -> {

                Column(
                    modifier =
                        Modifier.fillMaxSize()
                ) {

                    /*
                     * ARAMA
                     *
                     * Şimdilik sadece tasarım.
                     * Sonra ViewModel'e bağlarız.
                     */
                    OutlinedTextField(
                        value = "",
                        onValueChange = {},
                        placeholder = {
                            Text(
                                text =
                                    "Şirket ara..."
                            )
                        },
                        readOnly = true,
                        singleLine = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .widthIn(max = 720.dp)
                            .align(Alignment.CenterHorizontally)
                            .padding(
                                start = 16.dp,
                                end = 16.dp,
                                top = 16.dp
                            ),
                        shape =
                            RoundedCornerShape(
                                14.dp
                            )
                    )

                    /*
                     * YENİ ŞİRKET EKLE
                     */
                    Button(
                        onClick =
                            onCreateCompany,
                        modifier = Modifier
                            .fillMaxWidth()
                            .widthIn(max = 720.dp)
                            .align(Alignment.CenterHorizontally)
                            .padding(
                                horizontal =
                                    16.dp,
                                vertical =
                                    16.dp
                            )
                            .height(52.dp),
                        shape =
                            RoundedCornerShape(
                                14.dp
                            ),
                        colors =
                            ButtonDefaults
                                .buttonColors(
                                    containerColor =
                                        Orange,
                                    contentColor =
                                        Color.White
                                )
                    ) {

                        Text(
                            text =
                                "+  Yeni Şirket Ekle",
                            fontWeight =
                                FontWeight.Bold
                        )
                    }

                    /*
                     * EMPTY
                     */
                    if (
                        uiState.companies
                            .isEmpty()
                    ) {

                        Box(
                            modifier =
                                Modifier.fillMaxSize(),
                            contentAlignment =
                                Alignment.Center
                        ) {

                            Text(
                                text =
                                    "Henüz kayıtlı şirket bulunmuyor.",
                                color = Navy
                            )
                        }

                    } else {

                        /*
                         * ŞİRKET LİSTESİ
                         */
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxSize()
                                .widthIn(max = 720.dp)
                                .align(Alignment.CenterHorizontally),
                            contentPadding =
                                PaddingValues(
                                    horizontal =
                                        16.dp,
                                    vertical =
                                        4.dp
                                ),
                            verticalArrangement =
                                Arrangement.spacedBy(
                                    10.dp
                                )
                        ) {

                            items(
                                items =
                                    uiState.companies,
                                key = { company ->
                                    company.id
                                }
                            ) { company ->

                                CompanyCard(
                                    company =
                                        company,

                                    onClick = {

                                        onCompanyClick(
                                            company.id
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
}
