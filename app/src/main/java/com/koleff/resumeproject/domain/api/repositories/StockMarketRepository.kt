package com.koleff.resumeproject.domain.api.repositories.interfaces

import com.koleff.resumeproject.domain.api.models.responses.GetStockDataResponse
import com.squareup.moshi.Json

interface StockMarketRepository {

    suspend fun getStockMarketData(
        stockTag: String,
        dateFrom: String,
        dateTo: String): GetStockDataResponse
}