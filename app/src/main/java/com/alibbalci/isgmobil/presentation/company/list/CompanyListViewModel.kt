
package com.alibbalci.isgmobil.presentation.company.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alibbalci.isgmobil.domain.usecase.company.GetCompaniesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CompanyListViewModel @Inject constructor(
    private val getCompaniesUseCase: GetCompaniesUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(CompanyListUiState())

    val uiState: StateFlow<CompanyListUiState> =
        _uiState.asStateFlow()

    init {
        loadCompanies()
    }

    fun loadCompanies() {
        viewModelScope.launch {

            _uiState.value = _uiState.value.copy(
                isLoading = true,
                errorMessage = null
            )

            getCompaniesUseCase()
                .onSuccess { companies ->

                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        companies = companies,
                        errorMessage = null
                    )
                }
                .onFailure { error ->

                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        errorMessage = error.message
                            ?: "Şirketler yüklenirken bir hata oluştu."
                    )
                }
        }
    }
}

