package com.koleff.resumeproject.domain.wrappers

sealed class ResultWrapper <T> (val data: T? = null, val message: String? = null){
    class Success<T>(data: T) : ResultWrapper<T>(data)
    class Error<T>(data: T? = null, message: String?) : ResultWrapper<T>(data, message)
    class Loading<T>(data: T? = null) : ResultWrapper<T>(data)
}