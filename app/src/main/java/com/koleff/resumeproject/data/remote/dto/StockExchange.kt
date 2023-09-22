package com.koleff.resumeproject.data.remote.dto

import com.squareup.moshi.Json

data class StockExchange(
    @field:Json(name = "acronym")
    val acronym: String,
    @field:Json(name = "city")
    val city: String,
    @field:Json(name = "country")
    val country: String,
    @Json(name = "country_code")
    val countryCode: String,
    @field:Json(name = "mic")
    val mic: String,
    @field:Json(name = "name")
    val name: String,
    @field:Json(name = "website")
    val website: String
)