package com.alibbalci.isgmobil.presentation.observation.create

import android.net.Uri
import com.alibbalci.isgmobil.domain.model.Company
import com.alibbalci.isgmobil.domain.model.ObservationAnalysis

data class ObservationCreateUiState(

    val companies: List<Company> = emptyList(),
    val selectedCompany: Company? = null,
    val isLoadingCompanies: Boolean = false,

    val photoUri: Uri? = null,

    val isAnalyzing: Boolean = false,
    val analysisResult: ObservationAnalysis? = null,

    val selectedRiskCode: String? = null,
    val selectedSuggestion: String? = null,

    val errorMessage: String? = null,

    val isConfirming: Boolean = false,
    val confirmationError: String? = null,
    val confirmationSuccess: Boolean = false
)