package com.alibbalci.isgmobil.domain.usecase.auth

sealed class LoginValidationError(
    override val message: String
) : IllegalArgumentException(message) {

    data object EmptyEmail : LoginValidationError(
        message = "E-posta boş bırakılamaz."
    )

    data object InvalidEmail : LoginValidationError(
        message = "Geçerli bir e-posta adresi giriniz."
    )

    data object EmptyPassword : LoginValidationError(
        message = "Şifre boş bırakılamaz."
    )
}