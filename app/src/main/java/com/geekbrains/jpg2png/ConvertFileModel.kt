package com.geekbrains.popularlib

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import java.io.ByteArrayOutputStream

class ConvertFileModel(
    val applicationContext: Context
) {

    val imageJpgFileName = "Penguins.jpg"
    val imagePngFileName = "Penguins.png"
    lateinit var imageJpg: Bitmap
    lateinit var imagePng: Bitmap

    fun getJpg(): Single<Bitmap> = Single.fromCallable {
        val stream = applicationContext.assets.open(imageJpgFileName)
        imageJpg = BitmapFactory.decodeStream(stream)
        return@fromCallable imageJpg
    }

    fun savePng(): Completable = Completable.fromCallable {
        applicationContext.openFileOutput(imagePngFileName, Context.MODE_PRIVATE).use {
            imagePng.compress(Bitmap.CompressFormat.PNG, 100, it)
            it.close()
        }
    }

    fun getPng(): Single<Bitmap> = Single.fromCallable {
        imagePng = imageJpg.compress(Bitmap.CompressFormat.PNG)
        return@fromCallable imagePng
    }


    // Extension function to compress and change bitmap image format programmatically
    fun Bitmap.compress(
        format: Bitmap.CompressFormat = Bitmap.CompressFormat.JPEG,
        quality: Int = 100
    ): Bitmap {
        // Initialize a new ByteArrayStream
        val stream = ByteArrayOutputStream()

        // Compress the bitmap with JPEG format and quality 50%
        this.compress(
            format,
            quality,
            stream
        )

        val byteArray = stream.toByteArray()

        // Finally, return the compressed bitmap
        return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
    }
}


