package com.koleff.resumeproject.activities

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.koleff.resumeproject.activities.base.BaseActivity
import com.koleff.resumeproject.databinding.ActivityMainBinding

class MainActivity: BaseActivity<ActivityMainBinding>() {

    override val bindingInflater: (LayoutInflater) -> ActivityMainBinding =
        ActivityMainBinding::inflate
    override fun setup() {

    }

}