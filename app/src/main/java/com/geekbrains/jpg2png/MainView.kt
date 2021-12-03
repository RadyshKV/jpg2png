package com.geekbrains.popularlib

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

interface MainView : MvpView {
    @AddToEndSingle
    fun setButtonLoadJpg(text: String)
    fun setButtonConvertPng(text: String)
}