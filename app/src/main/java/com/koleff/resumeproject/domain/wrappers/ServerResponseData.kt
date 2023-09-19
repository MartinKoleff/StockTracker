package com.koleff.resumeproject.domain.wrappers

import com.koleff.resumeproject.R
import com.koleff.resumeproject.domain.models.responses.GetStockDataResponse
import com.koleff.resumeproject.domain.models.responses.BaseResponse

open class ServerResponseData(
    var isSuccessful: Boolean,
    val error: Error,
    val errorMessage: String?,
) {

    constructor(response: BaseResponse?) : this(
        response?.success ?: false,
        Error.fromErrorCode(response?.errorCode ?: 0).also {
            it.originalErrorMessage = response?.errorString
            it.originalErrorCode = response?.errorCode ?: 1
        },
        response?.errorString,
    )
}

enum class Error(
    val errorCode: Int,
    val errorMessage: Int,
    var errorType: ErrorType,
    var originalErrorMessage: String? = "",
    var originalErrorCode: Int = 1) {
    OK(0, R.string.text_success, ErrorType.SUCCESS),
    GENERIC(-1, R.string.text_internal_exception, ErrorType.INTERNAL);

    companion object {
        fun fromErrorCode(errorCode: Int): Error = values().find { it.errorCode == errorCode } ?: Error.GENERIC
    }
}

enum class ErrorType {
    SERVER, INTERNAL, LOGIN, SUCCESS, ACCESS_DENIED
}