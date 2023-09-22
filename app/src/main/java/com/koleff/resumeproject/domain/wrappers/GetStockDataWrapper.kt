package com.koleff.resumeproject.domain.wrappers

import com.koleff.resumeproject.domain.models.responses.GetStockDataResponse
import com.koleff.resumeproject.domain.wrappers.networkWrappers.ServerResponseData

class GetStockDataWrapper(getStockDataResponse: GetStockDataResponse) :
    ServerResponseData(getStockDataResponse) {
    val stockData = getStockDataResponse.stockData
    val paginationData = getStockDataResponse.paginationData
}