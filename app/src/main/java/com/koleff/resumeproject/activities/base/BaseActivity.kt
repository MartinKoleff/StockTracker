package com.koleff.resumeproject.activities.base

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.WindowManager
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity() {

    private var _binding: VB? = null
    abstract val bindingInflater: (LayoutInflater) -> VB

    val binding: VB
        get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = bindingInflater.invoke(layoutInflater)
        setContentView(binding!!.root)

//        val window = window
//        if (Build.VERSION.SDK_INT >= 21) {
//            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
//            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
////            window.statusBarColor = Colors.StatusBar[Colors.theme]
//        }
//
//        window.decorView.setBackgroundColor(-0x1)

        setup()
    }

    abstract fun setup()

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
