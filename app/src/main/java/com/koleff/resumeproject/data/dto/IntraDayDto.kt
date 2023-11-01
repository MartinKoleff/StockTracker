package com.koleff.resumeproject.data.dto

import com.squareup.moshi.Json

data class IntraDayDto(
    @field:Json(name = "open")
    val open: Double,
    @field:Json(name = "close")
    val close: Double?,
    @field:Json(name = "high")
    val high: Double,
    @field:Json(name = "low")
    val low: Double,
    @field:Json(name = "last")
    val last: Double?,
    @field:Json(name = "volume")
    val volume: Double?,
    @field:Json(name = "date")
    val date: String //TODO: convert to Date
)