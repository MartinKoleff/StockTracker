package com.koleff.resumeproject.domain.models.responses.baseResponse

import com.squareup.moshi.Json

open class BaseResponse(
    @field:Json(name = "error")
    val error: BaseResponseError? = null
)