package com.koleff.resumeproject.domain.wrappers

import com.koleff.resumeproject.domain.models.responses.GetTickersResponse
import com.koleff.resumeproject.domain.wrappers.networkWrappers.ServerResponseData

class GetTickersWrapper(getTickersResponse: GetTickersResponse) :
    ServerResponseData(getTickersResponse) {
    val tickers = getTickersResponse.tickers
    val paginationData = getTickersResponse.paginationData
}