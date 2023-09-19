package com.koleff.resumeproject.domain.api.services.implemetations

import com.koleff.resumeproject.domain.api.models.responses.GetStockDataResponse
import com.koleff.resumeproject.data.repositories.StockMarketRepositoryImpl
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

object StockMarketApiService {

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