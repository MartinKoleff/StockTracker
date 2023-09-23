package com.koleff.resumeproject.domain.wrappers.networkWrappers

import com.koleff.resumeproject.domain.models.responses.baseResponse.BaseResponse

open class ServerResponseData(
    var isSuccessful: Boolean,
    val error: KoleffError?,
    val errorMessage: String?,
) {
    constructor(response: BaseResponse?) : this(
        isSuccessful = response?.isSuccessful ?: false || response?.error == null,
        error = KoleffError.fromErrorCode(response?.error?.code).also {
            it.originalErrorMessage = response?.error?.message
            it.originalErrorCode = response?.error?.code
        },
        errorMessage = response?.error?.message
    )
}
