package com.koleff.resumeproject.data.dto

import com.squareup.moshi.Json

data class CurrencyDto(
    @field:Json(name = "code")
    val code: String,
    @field:Json(name = "symbol")
    val symbol: String,
    @field:Json(name = "name")
    val name: String,
)