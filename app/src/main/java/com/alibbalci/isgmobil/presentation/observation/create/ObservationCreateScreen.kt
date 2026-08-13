package com.alibbalci.isgmobil.presentation.observation.create

import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.alibbalci.isgmobil.core.photo.PhotoMultipartUtils
import com.alibbalci.isgmobil.core.photo.PhotoUriManager
import com.alibbalci.isgmobil.core.photo.PhotoValidationResult
import com.alibbalci.isgmobil.core.photo.PhotoValidator
import com.alibbalci.isgmobil.presentation.observation.create.components.AnalyzeButton
import com.alibbalci.isgmobil.presentation.observation.create.components.CompanySelector
import com.alibbalci.isgmobil.presentation.observation.create.components.ObservationHeader
import com.alibbalci.isgmobil.presentation.observation.create.components.PhotoSelectionCard
import com.alibbalci.isgmobil.presentation.observation.create.components.RiskCandidateCard
import com.alibbalci.isgmobil.ui.theme.Navy
import com.alibbalci.isgmobil.ui.theme.Orange
import com.alibbalci.isgmobil.ui.theme.RiskRed
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody

@Composable
fun ObservationCreateScreen(
    viewModel: ObservationCreateViewModel,
    onBack: () -> Unit,
    onConfirmationSuccess: () -> Unit
) {

    val uiState by viewModel.uiState.collectAsState()

    val context = LocalContext.current

    /*
     * Backend onay işlemi başarılı olduğunda
     * ViewModel:
     *
     * confirmationSuccess = true
     *
     * yapacak.
     *
     * Compose bu değişikliği görünce
     * navigation callback'ini çalıştıracak.
     */
    LaunchedEffect(uiState.confirmationSuccess) {

        if (uiState.confirmationSuccess) {
            onConfirmationSuccess()
        }
    }

    var cameraPhotoUri by remember {
        mutableStateOf<Uri?>(null)
    }

    var companyMenuExpanded by remember {
        mutableStateOf(false)
    }

    /*
     * KAMERA
     */
    val cameraLauncher =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.TakePicture()
        ) { success ->

            if (success) {

                cameraPhotoUri?.let { uri ->

                    when (
                        val result = PhotoValidator.validate(
                            context = context,
                            uri = uri
                        )
                    ) {

                        PhotoValidationResult.Valid -> {
                            viewModel.onPhotoSelected(uri)
                        }

                        is PhotoValidationResult.Invalid -> {
                            viewModel.setError(
                                result.message
                            )
                        }
                    }
                }
            }
        }

    /*
     * KAMERA İZNİ
     */
    val cameraPermissionLauncher =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.RequestPermission()
        ) { granted ->

            if (granted) {

                val uri =
                    PhotoUriManager
                        .createTemporaryPhotoUri(
                            context
                        )

                cameraPhotoUri = uri

                cameraLauncher.launch(uri)

            } else {

                viewModel.setError(
                    "Kamera kullanabilmek için kamera izni vermelisiniz."
                )
            }
        }

    /*
     * GALERİ
     */
    val photoPickerLauncher =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.PickVisualMedia()
        ) { uri ->

            if (uri != null) {

                when (
                    val result = PhotoValidator.validate(
                        context = context,
                        uri = uri
                    )
                ) {

                    PhotoValidationResult.Valid -> {
                        viewModel.onPhotoSelected(uri)
                    }

                    is PhotoValidationResult.Invalid -> {
                        viewModel.setError(
                            result.message
                        )
                    }
                }
            }
        }

    /*
     * FOTOĞRAF SEÇİCİYİ AÇAN
     * ORTAK FONKSİYON
     */
    fun openGallery() {

        photoPickerLauncher.launch(
            PickVisualMediaRequest(
                ActivityResultContracts
                    .PickVisualMedia
                    .ImageOnly
            )
        )
    }

    /*
     * KAMERAYI AÇAN
     * ORTAK FONKSİYON
     */
    fun openCamera() {

        val cameraPermissionGranted =
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED

        if (cameraPermissionGranted) {

            val uri =
                PhotoUriManager
                    .createTemporaryPhotoUri(
                        context
                    )

            cameraPhotoUri = uri

            cameraLauncher.launch(uri)

        } else {

            cameraPermissionLauncher.launch(
                Manifest.permission.CAMERA
            )
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {

        /*
         * NAVY HEADER
         */
        ObservationHeader(
            onBack = onBack
        )

        /*
         * SCROLL EDİLEBİLİR İÇERİK
         */
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(
                    rememberScrollState()
                )
                .padding(
                    horizontal = 20.dp,
                    vertical = 24.dp
                )
        ) {

            /*
             * FOTOĞRAF
             */
            PhotoSelectionCard(
                photoUri = uiState.photoUri,

                onTakePhoto = {
                    openCamera()
                },

                onPickFromGallery = {
                    openGallery()
                },

                onChangePhoto = {
                    openGallery()
                },

                onRemovePhoto = {
                    viewModel.removePhoto()
                }
            )

            Spacer(
                modifier = Modifier.height(28.dp)
            )

            /*
             * ŞİRKET
             */
            CompanySelector(
                companies = uiState.companies,

                selectedCompany =
                    uiState.selectedCompany,

                isLoading =
                    uiState.isLoadingCompanies,

                expanded =
                    companyMenuExpanded,

                onExpandedChange = {
                    companyMenuExpanded = it
                },

                onCompanySelected = { company ->
                    viewModel.selectCompany(
                        company
                    )
                }
            )

            /*
             * GENEL HATA MESAJI
             */
            uiState.errorMessage?.let { errorMessage ->

                Spacer(
                    modifier = Modifier.height(14.dp)
                )

                Text(
                    text = errorMessage,
                    color = RiskRed,
                    style =
                        MaterialTheme
                            .typography
                            .bodyMedium
                )
            }

            Spacer(
                modifier = Modifier.height(28.dp)
            )

            /*
             * ANALİZ ET
             */
            AnalyzeButton(
                enabled =
                    uiState.photoUri != null &&
                            uiState.selectedCompany != null,

                isAnalyzing =
                    uiState.isAnalyzing,

                onClick = {

                    val photoUri =
                        uiState.photoUri

                    val selectedCompany =
                        uiState.selectedCompany

                    if (photoUri == null) {

                        viewModel.setError(
                            "Lütfen bir fotoğraf seçin."
                        )

                        return@AnalyzeButton
                    }

                    if (selectedCompany == null) {

                        viewModel.setError(
                            "Lütfen bir şirket seçin."
                        )

                        return@AnalyzeButton
                    }

                    try {

                        val filePart =
                            PhotoMultipartUtils
                                .createMultipartFromUri(
                                    context = context,
                                    uri = photoUri
                                )

                        val companyIdBody =
                            selectedCompany.id
                                .toString()
                                .toRequestBody(
                                    "text/plain"
                                        .toMediaType()
                                )

                        viewModel.analyzePhoto(
                            file = filePart,
                            companyId = companyIdBody
                        )

                    } catch (exception: Exception) {

                        viewModel.setError(
                            exception.message
                                ?: "Fotoğraf hazırlanırken bir hata oluştu."
                        )
                    }
                }
            )

            /*
             * ANALİZ SONUCU
             */
            uiState.analysisResult?.let { result ->

                Spacer(
                    modifier = Modifier.height(32.dp)
                )

                Text(
                    text = "AI Analiz Sonucu",
                    color = Navy,
                    style =
                        MaterialTheme
                            .typography
                            .titleLarge
                )

                Spacer(
                    modifier = Modifier.height(10.dp)
                )

                Text(
                    text =
                        result.aiDescription
                            ?: "Açıklama bulunamadı.",
                    color = Navy,
                    style =
                        MaterialTheme
                            .typography
                            .bodyLarge
                )

                Spacer(
                    modifier = Modifier.height(28.dp)
                )

                Text(
                    text = "Tespit Edilen Riskler",
                    color = Navy,
                    style =
                        MaterialTheme
                            .typography
                            .titleMedium
                )

                Spacer(
                    modifier = Modifier.height(12.dp)
                )

                /*
                 * THRESHOLD'U GEÇEN TÜM RİSKLER
                 */
                result.riskCandidates
                    .forEachIndexed { index, risk ->

                        val isSelected =
                            uiState.selectedRiskCode ==
                                    risk.code

                        RiskCandidateCard(
                            index = index + 1,

                            risk = risk,

                            isSelected =
                                isSelected,

                            selectedSuggestion =
                                uiState.selectedSuggestion,

                            onRiskSelected = {

                                viewModel.selectRisk(
                                    risk.code
                                )
                            },

                            onSuggestionSelected = { suggestion ->

                                viewModel
                                    .selectSuggestion(
                                        suggestion
                                    )
                            }
                        )

                        Spacer(
                            modifier =
                                Modifier.height(12.dp)
                        )
                    }

                /*
                 * RİSK SEÇİLDİYSE
                 * ONAY BUTONU
                 *
                 * selectedSuggestion artık backend'e
                 * gönderilmediği için butonun görünmesi
                 * için suggestion seçilmesini beklemiyoruz.
                 */
                if (uiState.selectedRiskCode != null) {

                    Spacer(
                        modifier =
                            Modifier.height(12.dp)
                    )

                    Button(
                        onClick = {

                            /*
                             * Screen backend'i bilmiyor.
                             *
                             * Sadece ViewModel'e:
                             * "Kullanıcı onayladı"
                             * bilgisini gönderiyor.
                             */
                            viewModel.confirmObservation()
                        },

                        /*
                         * Backend isteği devam ederken
                         * kullanıcı tekrar basamasın.
                         */
                        enabled =
                            !uiState.isConfirming,

                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp),

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
                                if (uiState.isConfirming) {
                                    "Onaylanıyor..."
                                } else {
                                    "Seçimi Onayla"
                                },

                            style =
                                MaterialTheme
                                    .typography
                                    .labelLarge
                        )
                    }

                    /*
                     * CONFIRM İŞLEMİNE ÖZEL HATA
                     */
                    uiState.confirmationError
                        ?.let { errorMessage ->

                            Spacer(
                                modifier =
                                    Modifier.height(12.dp)
                            )

                            Text(
                                text = errorMessage,
                                color = RiskRed,
                                style =
                                    MaterialTheme
                                        .typography
                                        .bodyMedium
                            )
                        }
                }
            }

            Spacer(
                modifier = Modifier.height(40.dp)
            )
        }
    }
}