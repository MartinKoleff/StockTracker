package com.koleff.resumeproject.domain.models.responses

import com.koleff.resumeproject.data.dto.PaginationDataDto
import com.koleff.resumeproject.domain.models.responses.baseResponse.BaseResponse
import com.squareup.moshi.Json

data class GetTickersResponse(
    @Json(name = "data")
    val tickers: List<TickerDto>,
    @Json(name = "pagination")
    val paginationData: PaginationDataDto
): BaseResponse()