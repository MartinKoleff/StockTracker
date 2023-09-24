package com.koleff.resumeproject.domain.repositories

import com.koleff.resumeproject.domain.wrappers.GetStockDataWrapper
import com.koleff.resumeproject.domain.wrappers.GetTickerWrapper
import com.koleff.resumeproject.domain.wrappers.GetTickersWrapper
import com.koleff.resumeproject.domain.wrappers.networkWrappers.ResultWrapper
import kotlinx.coroutines.flow.Flow

interface StockMarketRepository {
    suspend fun getStockData(
        stockTag: String,
        dateFrom: String,
        dateTo: String): Flow<ResultWrapper<GetStockDataWrapper>>

    suspend fun getTickers(): Flow<ResultWrapper<GetTickersWrapper>>

    suspend fun getTicker(stockTag: String):  Flow<ResultWrapper<GetTickerWrapper>>
}