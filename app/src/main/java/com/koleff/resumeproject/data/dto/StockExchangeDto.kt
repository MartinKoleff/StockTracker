package com.koleff.resumeproject.data.dto

import com.squareup.moshi.Json

data class StockExchangeDto(
    @field:Json(name = "name")
    val name: String,
    @field:Json(name = "acronym")
    val acronym: String,
    @field:Json(name = "country")
    val country: String,
    @field:Json(name = "country_code")
    val countryCode: String,
    @field:Json(name = "city")
    val city: String,
    @field:Json(name = "website")
    val website: String,
    @field:Json(name = "timezone")
    val timezone: TimezoneDto,
    @field:Json(name = "currency")
    val currency: CurrencyDto
)