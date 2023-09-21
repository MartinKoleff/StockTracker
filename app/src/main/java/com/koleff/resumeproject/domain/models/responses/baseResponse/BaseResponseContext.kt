package com.koleff.resumeproject.domain.models.responses.baseResponse

import com.squareup.moshi.Json

data class BaseResponseContext(
    @field:Json(name = "symbols")
    val symbols: List<BaseResponseSymbol>
)