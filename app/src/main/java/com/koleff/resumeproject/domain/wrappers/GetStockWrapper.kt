package com.koleff.resumeproject.domain.wrappers

import com.koleff.resumeproject.domain.models.responses.GetStockResponse
import com.koleff.resumeproject.domain.wrappers.networkWrappers.ServerResponseData

class GetStockWrapper(getStockResponse: GetStockResponse) :
    ServerResponseData(getStockResponse) {
    val stock = getStockResponse.stock
}