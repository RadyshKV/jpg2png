package com.geekbrains.jpg2png

import android.graphics.Bitmap
import android.os.Bundle
import com.geekbrains.jpg2png.databinding.ActivityMainBinding
import com.geekbrains.popularlib.ConvertFileModel
import com.geekbrains.popularlib.MainView
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter


class MainActivity : MvpAppCompatActivity(), MainView {

    private var _binding: ActivityMainBinding? = null
    private val binding
        get() = _binding!!

    private val presenter by moxyPresenter { MainPresenter(ConvertFileModel(applicationContext)) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnLoadJpg.setOnClickListener { presenter.loadJpgClick() }
        binding.btnConvertPng.setOnClickListener { presenter.convertPngClick() }

    }

    override fun setImageJpg(image: Bitmap) {
        binding.imageJpg.setImageBitmap(image)
    }

    override fun setImagePng(image: Bitmap) {
        binding.imagePng.setImageBitmap(image)
    }

    override fun setTextComplete() {
        binding.textView.setText(R.string.completed)
    }
}