package com.alibbalci.isgmobil.core.photo

import android.content.Context
import android.net.Uri
import android.provider.OpenableColumns

object PhotoValidator {

    private const val MAX_FILE_SIZE_BYTES = 10L * 1024L * 1024L

    private val allowedMimeTypes = setOf(
        "image/jpeg",
        "image/png",
        "image/webp"
    )

    fun validate(
        context: Context,
        uri: Uri
    ): PhotoValidationResult {

        val contentResolver = context.contentResolver

        val mimeType = contentResolver.getType(uri)

        if (mimeType == null) {
            return PhotoValidationResult.Invalid(
                "Fotoğraf türü belirlenemedi."
            )
        }

        if (mimeType !in allowedMimeTypes) {
            return PhotoValidationResult.Invalid(
                "Sadece JPEG, PNG veya WEBP formatları destekleniyor."
            )
        }

        val fileSize = getFileSize(
            context = context,
            uri = uri
        )

        if (fileSize == null) {
            return PhotoValidationResult.Invalid(
                "Fotoğraf boyutu belirlenemedi."
            )
        }

        if (fileSize <= 0L) {
            return PhotoValidationResult.Invalid(
                "Geçersiz veya boş bir fotoğraf seçildi."
            )
        }

        if (fileSize > MAX_FILE_SIZE_BYTES) {
            return PhotoValidationResult.Invalid(
                "Fotoğraf boyutu 10 MB'dan büyük olamaz."
            )
        }

        return PhotoValidationResult.Valid
    }

    private fun getFileSize(
        context: Context,
        uri: Uri
    ): Long? {

        val cursor = context.contentResolver.query(
            uri,
            arrayOf(OpenableColumns.SIZE),
            null,
            null,
            null
        )

        cursor?.use {
            if (it.moveToFirst()) {

                val sizeIndex =
                    it.getColumnIndex(OpenableColumns.SIZE)

                if (sizeIndex != -1 && !it.isNull(sizeIndex)) {
                    return it.getLong(sizeIndex)
                }
            }
        }

        return try {
            context.contentResolver
                .openAssetFileDescriptor(uri, "r")
                ?.use { descriptor ->
                    descriptor.length
                        .takeIf { length -> length >= 0L }
                }
        } catch (_: Exception) {
            null
        }
    }
}