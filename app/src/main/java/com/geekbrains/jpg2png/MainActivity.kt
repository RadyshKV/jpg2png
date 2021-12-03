package com.geekbrains.jpg2png

import android.os.Bundle
import com.geekbrains.jpg2png.databinding.ActivityMainBinding
import com.geekbrains.popularlib.ConvertFileModel
import com.geekbrains.popularlib.MainPresenter
import com.geekbrains.popularlib.MainView
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter

class MainActivity : MvpAppCompatActivity(), MainView {

    private var _binding: ActivityMainBinding? = null
    private val binding
        get() = _binding!!

    @InjectPresenter
    lateinit var presenter: MainPresenter

    @ProvidePresenter
    fun providePresenter(): MainPresenter {
        return MainPresenter(ConvertFileModel())
    }

    // альтернатива передачи в Moxy Presenter'а с не дефолтным конструктором - через делегат moxyPresenter
    // private val presenter by moxyPresenter { MainPresenter(CountersModel()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnLoadJpg.setOnClickListener { presenter.loadJpgClick() }
        binding.btnConvertPng.setOnClickListener { presenter.convertPngClick() }
    }

    override fun setButtonLoadJpg(text: String) {
        binding.btnLoadJpg.text = R.string.load_jpg.toString()
    }

    override fun setButtonConvertPng(text: String) {
        binding.btnConvertPng.text = R.string.convert_to_png.toString()
    }

}