package com.koleff.resumeproject.domain.apiServices

import com.koleff.resumeproject.domain.repositories.StockMarketRepository
import com.koleff.resumeproject.domain.wrappers.ResultWrapper
import javax.inject.Inject

class StockMarketApiService @Inject constructor(
    private val stockMarketRepository: StockMarketRepository)
{
    suspend fun getStockData(
        stockTag: String,
        dateFrom: String,
        dateTo: String
    ) {
        val data = stockMarketRepository.getStockData(stockTag, dateFrom, dateTo)

        when (data){
            is ResultWrapper.Error -> TODO()
            is ResultWrapper.Loading -> TODO()
            is ResultWrapper.Success -> TODO()
        }
    }
}