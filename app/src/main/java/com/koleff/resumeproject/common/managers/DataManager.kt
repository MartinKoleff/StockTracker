package com.koleff.resumeproject.common.managers

import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.koleff.resumeproject.KoleffApp
import com.koleff.resumeproject.common.Constants
import com.koleff.resumeproject.domain.wrappers.StockData

object DataManager {
    //Constants
    private const val STOCKS_CACHE = "STOCKS_CACHE"

    private val sharedPreferences: SharedPreferences by lazy { KoleffApp.getSharedPreferences() }
    private val gson = Gson()
    var stocks: List<StockData> =
        Gson().fromJson(sharedPreferences.getString(STOCKS_CACHE, "[]"),
        object : TypeToken<List<StockData>>() {}.type
    )
        set(value){
            field != value || return

            field = value
            sharedPreferences.edit().putString(STOCKS_CACHE, gson.toJson(value)).apply()
            //Send broadcast to notify UI...
        }

    var selectedStock: StockData? = null

    val apiUrl = if(!Constants.useFakeApi) Constants.BASE_URL else Constants.FAKE_BASE_URL
}