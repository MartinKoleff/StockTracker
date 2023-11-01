package com.koleff.resumeproject.domain.models.requests

import com.koleff.resumeproject.data.dto.StockDto
import com.squareup.moshi.Json

data class SaveStocksRequest (
    @Json(name = "stocks")
    val stocks: List<StockDto>,
)
