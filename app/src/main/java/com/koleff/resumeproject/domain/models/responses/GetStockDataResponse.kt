package com.koleff.resumeproject.domain.models.responses

import com.koleff.resumeproject.data.remote.dto.PaginationDataDto
import com.koleff.resumeproject.data.remote.dto.StockDataDto
import com.squareup.moshi.Json

data class GetStockDataResponse (
    @field:Json(name = "pagination")
    val paginationData: PaginationDataDto,
    @field:Json(name = "data")
    val data: List<StockDataDto>,
): BaseResponse()