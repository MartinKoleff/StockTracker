package com.koleff.resumeproject.data.dto

import com.koleff.resumeproject.domain.wrappers.StockData
import com.squareup.moshi.Json

data class StockDto(
    @field:Json(name = "name")
    val name: String,
    @field:Json(name = "tag")
    val tag: String,
    @field:Json(name = "has_intraday")
    val hasIntraDay: Boolean,
    @field:Json(name = "has_end_of_day")
    val hasEndOfDay: Boolean,
    @field:Json(name = "end_of_day")
    val endOfDayList: List<EndOfDayDto>,
    @field:Json(name = "intra_day")
    val intraDayList: List<IntraDayDto>,
    @field:Json(name = "stock_exchange")
    val stockExchange: StockExchangeDto
)  {
    fun toStockData(): StockData {
        return StockData(this)
    }
}