package com.koleff.resumeproject.domain.apiServices.repositories.interfaces

import com.koleff.resumeproject.domain.apiServices.models.responses.GetStockDataResponse

interface StockMarketRepository {

    suspend fun getStockData(
        stockTag: String,
        dateFrom: String,
        dateTo: String): GetStockDataResponse
}