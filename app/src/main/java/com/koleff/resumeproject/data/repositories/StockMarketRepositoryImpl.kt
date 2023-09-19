package com.koleff.resumeproject.data.repositories

import com.koleff.resumeproject.data.remote.dto.StockMarketApi
import com.koleff.resumeproject.domain.apiServices.models.requests.GetStockDataBody
import com.koleff.resumeproject.domain.apiServices.models.responses.GetStockDataResponse
import com.koleff.resumeproject.domain.apiServices.repositories.interfaces.StockMarketRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class StockMarketRepositoryImpl
@Inject constructor(
    private val stockMarketApi: StockMarketApi,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : StockMarketRepository {

    override suspend fun getStockData(
        stockTag: String,
        dateFrom: String,
        dateTo: String
    ): GetStockDataResponse {
        val request = GetStockDataBody(
            stockTag,
            dateFrom,
            dateTo
        )

        return stockMarketApi.getStockData(request)
    }
}