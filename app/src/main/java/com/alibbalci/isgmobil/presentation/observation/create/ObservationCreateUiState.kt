package com.alibbalci.isgmobil.presentation.observation.create

import android.net.Uri
import com.alibbalci.isgmobil.domain.model.Company
import com.alibbalci.isgmobil.domain.model.ObservationAnalysis

data class ObservationCreateUiState(
    val photoUri: Uri? = null,

    val companies: List<Company> = emptyList(),
    val selectedCompany: Company? = null,

    val isLoadingCompanies: Boolean = false,
    val isAnalyzing: Boolean = false,
    val analysisResult: ObservationAnalysis? = null,
    val selectedSuggestion: String? = null,
    val selectedRiskCode: String? = null,

    val errorMessage: String? = null

)