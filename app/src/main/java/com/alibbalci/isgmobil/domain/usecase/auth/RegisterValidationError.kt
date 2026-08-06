package com.alibbalci.isgmobil.domain.usecase.auth

sealed class RegisterValidationError(
    override val message: String
) : IllegalArgumentException(message) {

    data object EmptyFullName : RegisterValidationError(
        message = "Ad soyad boş bırakılamaz."
    )

    data object EmptyEmail : RegisterValidationError(
        message = "E-posta boş bırakılamaz."
    )

    data object InvalidEmail : RegisterValidationError(
        message = "Geçerli bir e-posta adresi giriniz."
    )

    data object EmptyPassword : RegisterValidationError(
        message = "Şifre boş bırakılamaz."
    )

    data object ShortPassword : RegisterValidationError(
        message = "Şifre en az 6 karakter olmalıdır."
    )
}