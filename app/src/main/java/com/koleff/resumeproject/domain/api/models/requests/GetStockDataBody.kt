package com.koleff.resumeproject.domain.api.models.requests

import com.squareup.moshi.Json

data class GetStockDataBody (
    @field:Json(name = "symbols")
    val stockTag: String,
    @field:Json(name = "date_from")
    val dateFrom: String,
    @field:Json(name = "date_to")
    val dateTo: String,
)
