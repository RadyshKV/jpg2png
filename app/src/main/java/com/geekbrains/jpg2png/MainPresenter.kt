package com.geekbrains.jpg2png

import android.graphics.Bitmap
import android.util.Log
import com.geekbrains.popularlib.ConvertFileModel
import com.geekbrains.popularlib.MainView
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter

class MainPresenter(
    private val model: ConvertFileModel
) : MvpPresenter<MainView>() {


    fun loadJpgClick() {
        model.getJpg()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result: Bitmap ->
                    viewState.setImageJpg(result)
                },
                { e: Throwable ->
                    Log.e("jpg2png", "Ошибка файла Jpg $e", e)
                }
            )
    }

    fun convertPngClick() {
        model.getPng()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result: Bitmap ->
                    viewState.setImagePng(result)
                    this.savePngFile()
                },
                { e: Throwable ->
                    Log.e("jpg2png", "Ошибка файла Png $e", e)
                }
            )
    }

    private fun savePngFile() {
        model.savePng()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    viewState.setTextComplete()
                },
                { e: Throwable ->
                    Log.e("jpg2png", "Ошибка записи файла Png $e", e)
                })
    }


}