package com.koleff.resumeproject.data.remote.dto

import com.koleff.resumeproject.domain.wrappers.StockData
import com.squareup.moshi.Json

data class StockDataDto (
    @field:Json(name = "date")
    val date: String,
    @Json(name = "symbol")
    val stockTag: String,
    @Json(name = "exchange")
    val exchangeTag: String,
    @Json(name = "open")
    val openPrice: Double,
    @Json(name = "close")
    val closePrice: Double,
    @Json(name = "high")
    val highPrice: Double,
    @Json(name = "low")
    val lowPrice: Double,
    @field:Json(name = "volume")
    val volume: Double,
    @Json(name = "adj_open")
    val adjustOpenPrice: Double,
    @Json(name = "adj_close")
    val adjustClosePrice: Double,
    @Json(name = "adj_high")
    val adjustHighPrice: Double,
    @Json(name = "adj_low")
    val adjustLowPrice: Double,
    @Json(name = "adj_volume")
    val adjustVolume: Double
)

fun StockDataDto.toStockData(stockDataDto: StockDataDto): StockData {
    return StockData(stockDataDto)
}
