package com.koleff.resumeproject.activities.base

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity() {

    private var _binding: VB? = null
    abstract val bindingInflater: (LayoutInflater) -> VB

    private val binding: VB?
        get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = bindingInflater.invoke(layoutInflater)
        setContentView(binding!!.root)

        setup()
    }

    abstract fun setup()

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
