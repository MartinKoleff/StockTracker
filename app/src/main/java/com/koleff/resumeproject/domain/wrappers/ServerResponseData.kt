package com.koleff.resumeproject.domain.wrappers

import com.koleff.resumeproject.KoleffApp
import com.koleff.resumeproject.R
import com.koleff.resumeproject.domain.models.responses.baseResponse.BaseResponse

open class ServerResponseData(
    var isSuccessful: Boolean,
    val error: Error?,
    val errorMessage: String?,
) {

    constructor(response: BaseResponse?) : this(
        isSuccessful = response?.isSuccessful ?: false || response?.error == null,
        error = Error.fromErrorCode(response?.error?.code).also {
            it.originalErrorMessage = response?.error?.message
            it.originalErrorCode = response?.error?.code
        },
        errorMessage = response?.error?.message
    )
}

enum class Error(
    val errorCode: String,
    val errorMessage: Int,
    var errorType: ErrorType,
    var originalErrorMessage: String? = "",
    var originalErrorCode: String? = "") {

    OK("success", R.string.text_success, ErrorType.SUCCESS),
    GENERIC("error_generic", R.string.text_internal_exception, ErrorType.INTERNAL),
    INVALID_ACCESS_KEY("invalid_access_key", R.string.text_invalid_access_key, ErrorType.INTERNAL),
    MISSING_ACCESS_KEY("missing_access_key", R.string.text_missing_access_key, ErrorType.INTERNAL),
    INACTIVE_USER("inactive_user", R.string.text_inactive_user, ErrorType.INTERNAL),
    HTTPS_ACCESS_RESTRICTED("https_access_restricted", R.string.text_https_access_restricted, ErrorType.INTERNAL),
    FUNCTION_ACCESS_RESTRICTED("function_access_restricted", R.string.text_function_access_restricted, ErrorType.INTERNAL),
    INVALID_API_FUNCTION("invalid_api_function", R.string.text_invalid_api_function, ErrorType.INTERNAL),
    NOT_FOUND("404_not_found", R.string.text_not_found, ErrorType.INTERNAL),
    USAGE_LIMIT_REACHED("usage_limit_reached", R.string.text_usage_limit_reached, ErrorType.INTERNAL),
    RATE_LIMIT_REACHED("rate_limit_reached", R.string.text_rate_limit_reached, ErrorType.INTERNAL),
    INTERNAL_ERROR("internal_error", R.string.text_internal_error, ErrorType.INTERNAL);


    companion object {
        fun fromErrorCode(errorCode: String?): Error = values().find { it.errorCode == errorCode } ?: Error.GENERIC
    }
}

enum class ErrorType {
    SERVER, INTERNAL, LOGIN, SUCCESS, ACCESS_DENIED
}