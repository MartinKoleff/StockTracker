package com.koleff.resumeproject.domain.wrappers

import com.koleff.resumeproject.data.remote.dto.TickerDto
import com.koleff.resumeproject.domain.models.responses.GetTickerResponse
import com.koleff.resumeproject.domain.wrappers.networkWrappers.ServerResponseData

class GetTickerWrapper(getTickerResponse: GetTickerResponse) :
    ServerResponseData(getTickerResponse) {
    val ticker = (getTickerResponse as TickerDto).toTickerData()
}