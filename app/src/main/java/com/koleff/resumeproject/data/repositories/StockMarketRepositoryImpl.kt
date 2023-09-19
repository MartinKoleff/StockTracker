package com.koleff.resumeproject.data.repositories

import com.koleff.resumeproject.domain.api.models.responses.GetStockDataResponse
import com.koleff.resumeproject.domain.api.repositories.interfaces.StockMarketRepository
import com.koleff.resumeproject.domain.api.services.implemetations.StockMarketApiService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class StockMarketRepositoryImpl
    @Inject constructor(
        private val api: StockMarketApiService,
        private val dispatcher: CoroutineDispatcher = Dispatchers.IO) : StockMarketRepository {

    override suspend fun getStockMarketData(
        stockTag: String,
        dateFrom: String,
        dateTo: String
    ): GetStockDataResponse {

       return api.getStockMarketData(stockTag, dateFrom, dateTo)
    }
}