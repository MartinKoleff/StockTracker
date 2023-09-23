package com.koleff.resumeproject.domain.apiServices

import android.util.Log
import com.koleff.resumeproject.KoleffApp
import com.koleff.resumeproject.domain.repositories.StockMarketRepository
import com.koleff.resumeproject.domain.wrappers.networkWrappers.KoleffError
import com.koleff.resumeproject.domain.wrappers.networkWrappers.ResultWrapper
import javax.inject.Inject

class StockMarketApiService @Inject constructor(
    private val stockMarketRepository: StockMarketRepository
) {
    suspend fun getStockData(
        stockTag: String,
        dateFrom: String,
        dateTo: String
    ) {
        val apiResult = stockMarketRepository.getStockData(stockTag, dateFrom, dateTo)

        when (apiResult) {
            is ResultWrapper.ApiError -> {
            }

            is ResultWrapper.Loading -> TODO()
            is ResultWrapper.Success -> {
                Log.d(KoleffApp.TAG_LOG, apiResult.data.stockData.toString()) 
            }
        }
    }

    suspend fun getTickers() {
        val apiResult = stockMarketRepository.getTickers()

        when (apiResult) {
            is ResultWrapper.ApiError -> {
            }

            is ResultWrapper.Loading -> TODO()
            is ResultWrapper.Success -> {
                Log.d(KoleffApp.TAG_LOG, apiResult.data.tickers.toString())
            }
        }
    }

    suspend fun getTicker(stockTag: String) {
        val apiResult = stockMarketRepository.getTicker(stockTag)

        when (apiResult) {
            is ResultWrapper.ApiError -> {
            }

            is ResultWrapper.Loading -> TODO()
            is ResultWrapper.Success -> {
                Log.d(KoleffApp.TAG_LOG, apiResult.data.ticker.toString())
            }
        }
    }
}