package com.koleff.resumeproject.data.dto

import com.squareup.moshi.Json

data class EndOfDayDto(
    @field:Json(name = "open")
    val open: Double,
    @field:Json(name = "close")
    val close: Double,
    @field:Json(name = "high")
    val high: Double,
    @field:Json(name = "low")
    val low: Double,
    @field:Json(name = "volume")
    val volume: Double?,
    @field:Json(name = "adj_open")
    val adjustOpen: Double?,
    @field:Json(name = "adj_close")
    val adjustClose: Double?,
    @field:Json(name = "adj_high")
    val adjustHigh: Double?,
    @field:Json(name = "adj_low")
    val adjustLow: Double?,
    @field:Json(name = "adj_volume")
    val adjustVolume: Double?,
    @field:Json(name = "split_factor")
    val splitFactor: Double,
    @field:Json(name = "dividend")
    val dividend: Double,
    @field:Json(name = "date")
    val date: String //TODO: convert to Date
)