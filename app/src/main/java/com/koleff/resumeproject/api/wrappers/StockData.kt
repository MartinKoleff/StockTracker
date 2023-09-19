package com.koleff.resumeproject.api.wrappers

import com.squareup.moshi.Json

data class StockData (
    @field:Json(name = "date")
    val date: String,
    @field:Json(name = "symbols")
    val stockTag: String,
    @field:Json(name = "exchange")
    val exchangeTag: String,
    @field:Json(name = "open")
    val openPrice: Double,
    @field:Json(name = "close")
    val closePrice: Double,
    @field:Json(name = "high")
    val highPrice: Double,
    @field:Json(name = "low")
    val lowPrice: Double,
    @field:Json(name = "volume")
    val volume: Double,
    @field:Json(name = "adj_open")
    val adjustOpenPrice: Double,
    @field:Json(name = "adj_close")
    val adjustClosePrice: Double,
    @field:Json(name = "adj_high")
    val adjustHighPrice: Double,
    @field:Json(name = "adj_low")
    val adjustLowPrice: Double,
    @field:Json(name = "adj_volume")
    val adjustVolume: Double,
)
