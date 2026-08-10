package com.alibbalci.isgmobil.core.photo

import android.content.Context
import android.net.Uri
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import java.io.FileOutputStream

object PhotoMultipartUtils {

    fun createMultipartFromUri(
        context: Context,
        uri: Uri
    ): MultipartBody.Part {

        val contentResolver = context.contentResolver

        val mimeType =
            contentResolver.getType(uri)
                ?: "image/jpeg"

        val extension = when (mimeType) {
            "image/png" -> ".png"
            "image/webp" -> ".webp"
            else -> ".jpg"
        }

        val tempFile = File.createTempFile(
            "observation_upload_",
            extension,
            context.cacheDir
        )

        contentResolver
            .openInputStream(uri)
            ?.use { inputStream ->

                FileOutputStream(tempFile).use { outputStream ->
                    inputStream.copyTo(outputStream)
                }

            }
            ?: throw IllegalArgumentException(
                "Fotoğraf dosyası açılamadı."
            )

        val requestBody = tempFile.asRequestBody(
            mimeType.toMediaTypeOrNull()
        )

        return MultipartBody.Part.createFormData(
            name = "file",
            filename = tempFile.name,
            body = requestBody
        )
    }
}