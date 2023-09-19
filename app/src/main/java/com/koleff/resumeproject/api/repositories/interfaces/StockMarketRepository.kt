package com.koleff.resumeproject.api.repositories.interfaces

import com.koleff.resumeproject.api.models.responses.GetStockDataResponse
import com.squareup.moshi.Json

interface StockMarketRepository {

    suspend fun getStockMarketData(
        stockTag: String,
        dateFrom: String,
        dateTo: String): GetStockDataResponse
}