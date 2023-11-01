package com.koleff.resumeproject.data.dto

import com.squareup.moshi.Json

data class TimezoneDto(
    @field:Json(name = "timezone")
    val timezone: String,
    @field:Json(name = "abbreviation")
    val abbreviation: String,
    @field:Json(name = "abbreviation_dst")
    val abbreviationDst: String,
)