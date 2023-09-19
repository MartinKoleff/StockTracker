package com.koleff.resumeproject.domain.models.responses

import com.squareup.moshi.Json

open class BaseResponse {
    @field:Json(name = "ErrorCode")
    val errorCode: Int = 0
    @field:Json(name = "ErrorMsg")
    val errorMsg: String? = null
    @field:Json(name = "ErrorString")
    val errorString: String? = null
    @field:Json(name = "Success")
    val success: Boolean = false
}