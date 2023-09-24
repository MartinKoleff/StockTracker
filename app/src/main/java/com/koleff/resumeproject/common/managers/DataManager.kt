package com.koleff.resumeproject.common.managers

import com.koleff.resumeproject.common.Constants
import com.koleff.resumeproject.domain.wrappers.StockData
import com.koleff.resumeproject.domain.wrappers.TickerData

object DataManager {
    val apiUrl = if(!Constants.useFakeApi) Constants.BASE_URL else Constants.FAKE_BASE_URL

//    val sharedPreferences: SharedPreferences by lazy { KoleffApp.getSharedPreferences() }

    lateinit var stocks: List<StockData>
    lateinit var tickers: List<TickerData>
    lateinit var selectedTicker: TickerData
}