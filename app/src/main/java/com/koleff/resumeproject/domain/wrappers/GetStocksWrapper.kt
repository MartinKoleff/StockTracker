package com.koleff.resumeproject.domain.wrappers

import com.koleff.resumeproject.domain.models.responses.GetStocksResponse
import com.koleff.resumeproject.domain.wrappers.networkWrappers.ServerResponseData

class GetStocksWrapper(getStocksResponse: GetStocksResponse) :
    ServerResponseData(getStocksResponse) {
    val stockData = getStocksResponse.stockData
    val paginationData = getStocksResponse.paginationData
}