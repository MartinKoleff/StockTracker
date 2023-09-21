package com.koleff.resumeproject.domain.wrappers.networkWrappers

sealed class ResultWrapper<out T>{
    class Success<out T>(val data: T) : ResultWrapper<T>()
    class KoleffError<out T>(
        val error: Error? = null,
        val errorMessage: String? = null,
        val value: T? = null) : ResultWrapper<T>()
    class Loading<T> : ResultWrapper<T>()
}