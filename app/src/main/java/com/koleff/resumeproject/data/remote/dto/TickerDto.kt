package com.koleff.resumeproject.data.remote.dto

import com.koleff.resumeproject.domain.wrappers.StockData
import com.koleff.resumeproject.domain.wrappers.TickerData
import com.squareup.moshi.Json

data class TickerDto(
    @Json(name = "country")
    val country: String?,
    @Json(name = "has_eod")
    val hasEod: Boolean,
    @Json(name = "has_intraday")
    val hasIntraday: Boolean,
    @Json(name = "name")
    val name: String,
    @Json(name = "stock_exchange")
    val stockExchange: StockExchange,
    @Json(name = "symbol")
    val stockTag: String
){
    fun toTickerData(): TickerData {
        return TickerData(this)
    }
}

