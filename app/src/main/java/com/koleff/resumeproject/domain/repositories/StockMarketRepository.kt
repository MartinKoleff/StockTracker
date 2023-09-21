package com.koleff.resumeproject.domain.repositories

import com.koleff.resumeproject.domain.wrappers.GetStockDataWrapper
import com.koleff.resumeproject.domain.wrappers.GetTickerWrapper
import com.koleff.resumeproject.domain.wrappers.GetTickersWrapper
import com.koleff.resumeproject.domain.wrappers.networkWrappers.ResultWrapper

interface StockMarketRepository {
    suspend fun getStockData(
        stockTag: String,
        dateFrom: String,
        dateTo: String): ResultWrapper<GetStockDataWrapper>

    suspend fun getTickers(): ResultWrapper<GetTickersWrapper>

    suspend fun getTicker(stockTag: String): ResultWrapper<GetTickerWrapper>
}