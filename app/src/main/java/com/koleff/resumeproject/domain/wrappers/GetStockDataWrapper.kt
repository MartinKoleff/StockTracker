package com.koleff.resumeproject.domain.wrappers

import com.koleff.resumeproject.domain.models.responses.GetStockDataResponse

class GetStockDataWrapper(getStockDataResponse: GetStockDataResponse):
    ServerResponseData(getStockDataResponse) {
        val data = getStockDataResponse.data
}