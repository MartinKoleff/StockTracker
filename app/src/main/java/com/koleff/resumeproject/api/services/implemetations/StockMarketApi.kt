package com.koleff.resumeproject.api.services.implemetations

import com.koleff.resumeproject.KoleffApp
import com.koleff.resumeproject.api.models.responses.GetStockDataResponse
import com.koleff.resumeproject.api.repositories.implemetations.StockMarketRepositoryImpl
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

object StockMarketApi {

    suspend fun getStockMarketData(
        stockTag: String,
        dateFrom: String,
        dateTo: String
    ): GetStockDataResponse {

        GlobalScope.launch { //active activity lifecycle owner...
           val data = StockMarketRepositoryImpl().getStockMarketData(stockTag, dateFrom, dateTo)

            when(data){

            }
        }
    }
}