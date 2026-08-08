
package com.alibbalci.isgmobil.presentation.company.create

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alibbalci.isgmobil.domain.usecase.company.CreateCompanyUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CompanyCreateViewModel @Inject constructor(
    private val createCompanyUseCase: CreateCompanyUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(CompanyCreateUiState())

    val uiState: StateFlow<CompanyCreateUiState> =
        _uiState.asStateFlow()

    fun onNameChange(value: String) {
        _uiState.value = _uiState.value.copy(
            name = value
        )
    }

    fun onAddressChange(value: String) {
        _uiState.value = _uiState.value.copy(
            address = value
        )
    }

    fun onHazardClassChange(value: String) {
        _uiState.value = _uiState.value.copy(
            hazardClass = value
        )
    }

    fun onPhoneChange(value: String) {
        _uiState.value = _uiState.value.copy(
            phone = value
        )
    }

    fun onOccupationalPhysicianChange(value: String) {
        _uiState.value = _uiState.value.copy(
            occupationalPhysician = value
        )
    }

    fun createCompany() {
        val currentState = _uiState.value

        viewModelScope.launch {

            _uiState.value = currentState.copy(
                isLoading = true,
                errorMessage = null
            )

            createCompanyUseCase(
                name = currentState.name,
                address = currentState.address,
                hazardClass = currentState.hazardClass,
                phone = currentState.phone,
                occupationalPhysician = currentState.occupationalPhysician
            )
                .onSuccess {

                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        isCreated = true,
                        errorMessage = null
                    )
                }
                .onFailure { error ->

                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        isCreated = false,
                        errorMessage = error.message
                            ?: "Şirket oluşturulurken bir hata oluştu."
                    )
                }
        }
    }

    fun consumeCreatedEvent() {
        _uiState.value = _uiState.value.copy(
            isCreated = false
        )
    }
}

