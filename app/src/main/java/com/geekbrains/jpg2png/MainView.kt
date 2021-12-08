package com.geekbrains.popularlib

import android.graphics.Bitmap
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

interface MainView : MvpView {
    @AddToEndSingle
    fun setImageJpg(image : Bitmap)
    @AddToEndSingle
    fun setImagePng(image :Bitmap)
    @AddToEndSingle
    fun setTextComplete()
}