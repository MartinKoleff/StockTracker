package com.koleff.resumeproject.domain.repositories

import com.koleff.resumeproject.data.dto.StockDto
import com.koleff.resumeproject.domain.wrappers.GetStockWrapper
import com.koleff.resumeproject.domain.wrappers.GetStocksWrapper
import com.koleff.resumeproject.domain.wrappers.networkWrappers.ResultWrapper
import com.koleff.resumeproject.domain.wrappers.networkWrappers.ServerResponseData
import kotlinx.coroutines.flow.Flow

interface StockRepository {
    suspend fun getStocks(): Flow<ResultWrapper<GetStocksWrapper>>

    //TODO: implement with date filtration...
    suspend fun getStock(stockTag: String,
                         dateFrom: String? = null,
                         dateTo: String? = null): Flow<ResultWrapper<GetStockWrapper>>

    suspend fun saveStock(stock: StockDto): Flow<ResultWrapper<ServerResponseData>>

    suspend fun saveStocks(stocks: List<StockDto>): Flow<ResultWrapper<ServerResponseData>>

    //TODO: add Delete and Update...
}