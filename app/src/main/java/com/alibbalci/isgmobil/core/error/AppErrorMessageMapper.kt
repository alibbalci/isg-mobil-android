package com.alibbalci.isgmobil.core.error

fun AppError.toUserMessage(): String {
    return when (this) {

        AppError.Network -> {
            "İnternet bağlantısı kurulamadı."
        }

        AppError.Unauthorized -> {
            "E-posta veya şifre hatalı."
        }

        AppError.NotFound -> {
            "Aradığınız kayıt bulunamadı."
        }

        AppError.Conflict -> {
            "Bu e-posta zaten kullanılıyor."
        }

        AppError.ServerUnavailable -> {
            "Sunucu şu anda kullanılamıyor."
        }

        is AppError.Validation -> {
            message
        }

        is AppError.Unknown -> {
            "Beklenmeyen bir hata oluştu."
        }
    }
}