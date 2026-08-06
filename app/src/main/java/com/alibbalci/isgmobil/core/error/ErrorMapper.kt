package com.alibbalci.isgmobil.core.error

import com.google.gson.Gson
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ErrorMapper @Inject constructor(
    private val gson: Gson
) {

    fun map(throwable: Throwable): AppError {
        return when (throwable) {

            is IOException -> {
                AppError.Network
            }

            is HttpException -> {
                mapHttpException(throwable)
            }

            else -> {
                AppError.Unknown(
                    message = throwable.message
                )
            }
        }
    }

    private fun mapHttpException(
        exception: HttpException
    ): AppError {

        val statusCode = exception.code()

        val apiError = parseApiError(exception)

        return when (statusCode) {

            400 -> {
                AppError.Validation(
                    message = apiError?.message
                        ?: "Geçersiz istek"
                )
            }

            401 -> {
                AppError.Unauthorized
            }

            404 -> {
                AppError.NotFound
            }

            409 -> {
                AppError.Conflict
            }

            502, 503 -> {
                AppError.ServerUnavailable
            }

            else -> {
                AppError.Unknown(
                    message = apiError?.message
                )
            }
        }
    }

    private fun parseApiError(
        exception: HttpException
    ): ApiErrorResponse? {
        return try {
            val errorBody = exception
                .response()
                ?.errorBody()
                ?.string()

            if (errorBody.isNullOrBlank()) {
                null
            } else {
                gson.fromJson(
                    errorBody,
                    ApiErrorResponse::class.java
                )
            }
        } catch (exception: Exception) {
            null
        }
    }
}