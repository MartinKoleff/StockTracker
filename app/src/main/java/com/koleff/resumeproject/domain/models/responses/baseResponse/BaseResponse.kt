package com.koleff.resumeproject.domain.models.responses.baseResponse

import com.squareup.moshi.Json

open class BaseResponse(
    @Json(name = "success")
    val isSuccessful: Boolean = true,
    @field:Json(name = "error")
    val error: BaseResponseError? = null
)