package com.geekbrains.popularlib

import moxy.MvpPresenter

class MainPresenter(
    private val model: ConvertFileModel
): MvpPresenter<MainView>(){

    fun loadJpgClick() {
        viewState.setButtonLoadJpg("Any")
    }

    fun convertPngClick() {
        viewState.setButtonConvertPng("Any")
    }
}