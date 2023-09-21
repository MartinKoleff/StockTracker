package com.koleff.resumeproject.data.repositories

import com.koleff.resumeproject.common.Network
import com.koleff.resumeproject.data.remote.StockMarketApi
import com.koleff.resumeproject.domain.repositories.StockMarketRepository
import com.koleff.resumeproject.domain.wrappers.GetStockDataWrapper
import com.koleff.resumeproject.domain.wrappers.GetTickerWrapper
import com.koleff.resumeproject.domain.wrappers.GetTickersWrapper
import com.koleff.resumeproject.domain.wrappers.networkWrappers.ResultWrapper
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
        return Network.executeApiCall(dispatcher,
            {
                GetStockDataWrapper(
                    stockMarketApi.getStockData(
                        stockTag,
                        dateFrom,
                        dateTo
                    )
                )
            })
    }

    override suspend fun getTickers(): ResultWrapper<GetTickersWrapper> {
        return Network.executeApiCall(dispatcher,
            {
                GetTickersWrapper(
                    stockMarketApi.getTickers()
                )
            })
    }

    override suspend fun getTicker(stockTag: String): ResultWrapper<GetTickerWrapper> {
        return Network.executeApiCall(dispatcher,
            {
                GetTickerWrapper(
                    stockMarketApi.getTicker(stockTag)
                )
            })
    }
}