package com.alibbalci.isgmobil.core.photo

import android.content.Context
import android.net.Uri
import androidx.core.content.FileProvider
import java.io.File

object PhotoUriManager {

    fun createTemporaryPhotoUri(
        context: Context
    ): Uri {

        val photoDirectory = File(
            context.cacheDir,
            "photos"
        )

        if (!photoDirectory.exists()) {
            photoDirectory.mkdirs()
        }

        val photoFile = File.createTempFile(
            "observation_",
            ".jpg",
            photoDirectory
        )

        return FileProvider.getUriForFile(
            context,
            "${context.packageName}.fileprovider",
            photoFile
        )
    }
}