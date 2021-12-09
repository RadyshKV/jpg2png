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
        val stream = ByteArrayOutputStream()
        imageJpg.compress(Bitmap.CompressFormat.PNG, 100, stream)
        val byteArray = stream.toByteArray()
        imagePng = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
        return@fromCallable imagePng
    }
}


