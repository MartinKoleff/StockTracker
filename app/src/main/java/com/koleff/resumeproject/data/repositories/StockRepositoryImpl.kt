package com.koleff.resumeproject.data.repositories

import com.koleff.resumeproject.common.Network
import com.koleff.resumeproject.data.dto.StockDto
import com.koleff.resumeproject.data.remote.StockApi
import com.koleff.resumeproject.domain.models.requests.GetStocksRequest
import com.koleff.resumeproject.domain.models.requests.SaveStockRequest
import com.koleff.resumeproject.domain.models.requests.SaveStocksRequest
import com.koleff.resumeproject.domain.repositories.StockRepository
import com.koleff.resumeproject.domain.wrappers.GetStockWrapper
import com.koleff.resumeproject.domain.wrappers.GetStocksWrapper
import com.koleff.resumeproject.domain.wrappers.networkWrappers.ResultWrapper
import com.koleff.resumeproject.domain.wrappers.networkWrappers.ServerResponseData
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class StockRepositoryImpl
@Inject constructor(
    private val stockMarketApi: StockApi,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : StockRepository {
    override suspend fun getStocks(): Flow<ResultWrapper<GetStocksWrapper>> {
        return Network.executeApiCall(dispatcher,
            {
                GetStocksWrapper(stockMarketApi.getStocks())
            })
    }

    override suspend fun getStock(
        stockTag: String,
        dateFrom: String?,
        dateTo: String?
    ): Flow<ResultWrapper<GetStockWrapper>> {
        val request = GetStocksRequest(
            stockTag,
            dateFrom,
            dateTo
        )

        return Network.executeApiCall(dispatcher,
            {
                GetStockWrapper(
                    stockMarketApi.getStock(stockTag, request)
                )
            })
    }

    override suspend fun saveStock(stock: StockDto): Flow<ResultWrapper<ServerResponseData>> {
        val request = SaveStockRequest(
            stock
        )

        return Network.executeApiCall(dispatcher,
            {
                ServerResponseData(
                    stockMarketApi.saveStock(stock.tag, request)
                )
            })
    }

    override suspend fun saveStocks(stocks: List<StockDto>): Flow<ResultWrapper<ServerResponseData>> {
        val request = SaveStocksRequest(
            stocks
        )

        return Network.executeApiCall(dispatcher,
            {
                ServerResponseData(
                    stockMarketApi.saveStocks(request)
                )
            })
    }
}