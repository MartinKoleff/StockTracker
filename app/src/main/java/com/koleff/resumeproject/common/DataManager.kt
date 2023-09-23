package com.koleff.resumeproject.common

import android.content.SharedPreferences

object DataManager {
    val apiUrl = if(!Constants.useFakeApi) Constants.BASE_URL else Constants.FAKE_BASE_URL

//    val sharedPreferences: SharedPreferences by lazy { KoleffApp.getSharedPreferences() }
}