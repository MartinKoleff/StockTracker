package com.koleff.resumeproject.domain.wrappers

import com.koleff.resumeproject.domain.models.responses.baseResponse.BaseResponseError

sealed class ResultWrapper<out T>{
    class Success<out T>(val data: T) : ResultWrapper<T>()
    class KoleffError<out T>(
        val error: Error? = null,
        val errorMessage: String? = null,
        val value: T? = null) : ResultWrapper<T>()
    class Loading<out T>(val data: T? = null) : ResultWrapper<T>()
}