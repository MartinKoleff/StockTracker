package com.koleff.resumeproject.api.repositories.implemetations

import com.koleff.resumeproject.api.models.responses.GetStockDataResponse
import com.koleff.resumeproject.api.repositories.interfaces.StockMarketRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class StockMarketRepositoryImpl(
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO) : StockMarketRepository {

    override suspend fun getStockMarketData(
        stockTag: String,
        dateFrom: String,
        dateTo: String
    ): GetStockDataResponse {
        TODO("Not yet implemented")
    }
}