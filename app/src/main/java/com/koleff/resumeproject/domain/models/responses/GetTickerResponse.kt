package com.koleff.resumeproject.domain.models.responses

import com.koleff.resumeproject.domain.models.responses.baseResponse.BaseResponse
import com.squareup.moshi.Json

data class GetTickerResponse(
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
): BaseResponse()