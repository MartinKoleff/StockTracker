package com.koleff.resumeproject.domain.apiServices

import android.util.Log
import com.koleff.resumeproject.KoleffApp
import com.koleff.resumeproject.common.DataManager
import com.koleff.resumeproject.data.remote.dto.StockDataDto
import com.koleff.resumeproject.data.remote.dto.TickerDto
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
                DataManager.stocks =
                    apiResult.data.stockData.map(StockDataDto::toStockData).also {
                        Log.d(KoleffApp.TAG_LOG, it.toString())
                    }
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
                DataManager.tickers =
                    apiResult.data.tickers.map(TickerDto::toTickerData).also {
                        Log.d(KoleffApp.TAG_LOG, it.toString())
                    }
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
                DataManager.selectedTicker = apiResult.data.ticker.also {
                    Log.d(KoleffApp.TAG_LOG, it.toString())
                }
            }
        }
    }
}