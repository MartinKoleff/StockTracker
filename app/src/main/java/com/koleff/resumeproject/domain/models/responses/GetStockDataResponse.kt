package com.koleff.resumeproject.domain.models.responses

import com.koleff.resumeproject.data.remote.dto.PaginationDataDto
import com.koleff.resumeproject.data.remote.dto.StockDataDto
import com.koleff.resumeproject.domain.models.responses.baseResponse.BaseResponse
import com.squareup.moshi.Json

data class GetStockDataResponse (
    @Json(name = "pagination")
    val paginationData: PaginationDataDto,
    @Json(name = "data")
    val stockData: List<StockDataDto>,
): BaseResponse()