package com.koleff.resumeproject.domain.models.requests

import com.squareup.moshi.Json

data class GetStockDataBody (
    @Json(name = "symbol")
    val stockTag: String,
    @Json(name = "date_from")
    val dateFrom: String,
    @Json(name = "date_to")
    val dateTo: String,
)
