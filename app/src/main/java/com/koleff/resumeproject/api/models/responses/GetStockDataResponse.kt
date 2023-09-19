package com.koleff.resumeproject.api.models.responses

import com.koleff.resumeproject.api.wrappers.PaginationData
import com.koleff.resumeproject.api.wrappers.StockData
import com.squareup.moshi.Json

data class GetStockDataResponse (
    @field:Json(name = "pagination")
    val paginationData: PaginationData,
    @field:Json(name = "data")
    val data: List<StockData>,
)