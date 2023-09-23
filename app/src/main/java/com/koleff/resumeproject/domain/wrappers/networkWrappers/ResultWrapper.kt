package com.koleff.resumeproject.domain.wrappers.networkWrappers

sealed class ResultWrapper<out T>{
    class Success<out T>(val data: T) : ResultWrapper<T>()
    class ApiError<out T>(
        val error: KoleffError? = null,
        val errorMessage: String? = null,
        val value: T? = null) : ResultWrapper<T>()
    class Loading<T> : ResultWrapper<T>()
}