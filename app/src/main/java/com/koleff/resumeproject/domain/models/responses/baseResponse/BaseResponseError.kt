package com.koleff.resumeproject.domain.models.responses.baseResponse

import com.squareup.moshi.Json

data class BaseResponseError(
    @field:Json(name = "code")
    val code: String,
    @field:Json(name = "context")
    val context: BaseResponseContext,
    @field:Json(name = "message")
    val message: String
)