package com.koleff.resumeproject.domain.repositories

import com.koleff.resumeproject.domain.models.responses.GetStockDataResponse
import com.koleff.resumeproject.domain.wrappers.GetStockDataWrapper
import com.koleff.resumeproject.domain.wrappers.ResultWrapper
import com.koleff.resumeproject.domain.wrappers.ServerResponseData

interface StockMarketRepository {
    suspend fun getStockData(
        stockTag: String,
        dateFrom: String,
        dateTo: String): ResultWrapper<GetStockDataWrapper>
}