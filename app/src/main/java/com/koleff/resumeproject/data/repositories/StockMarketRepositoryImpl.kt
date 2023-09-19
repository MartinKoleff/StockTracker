package com.koleff.resumeproject.data.repositories

import com.koleff.resumeproject.common.Network
import com.koleff.resumeproject.data.remote.StockMarketApi
import com.koleff.resumeproject.domain.apiServices.models.requests.GetStockDataBody
import com.koleff.resumeproject.domain.repositories.StockMarketRepository
import com.koleff.resumeproject.domain.wrappers.GetStockDataWrapper
import com.koleff.resumeproject.domain.wrappers.ResultWrapper
import com.koleff.resumeproject.domain.wrappers.ServerResponseData
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
    ): ResultWrapper<GetStockDataWrapper> {
        val request = GetStockDataBody(
            stockTag,
            dateFrom,
            dateTo
        )

        return Network.executeApiCall(dispatcher,{ GetStockDataWrapper(stockMarketApi.getStockData(request)) }) //GetStockDataWrapper
    }
}