package com.koleff.resumeproject.domain.models.responses.baseResponse

import com.squareup.moshi.Json

data class BaseResponseSymbol(
    @field:Json(name = "key")
    val key: String,
    @field:Json(name = "message")
    val message: String
)