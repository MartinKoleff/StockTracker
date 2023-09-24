package com.koleff.resumeproject.common

import com.koleff.resumeproject.domain.wrappers.networkWrappers.ResultWrapper
import com.koleff.resumeproject.domain.wrappers.networkWrappers.ServerResponseData
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

object Network {
    private const val MAX_RETRY_COUNT = 1

    suspend fun <T> executeApiCall(
        dispatcher: CoroutineDispatcher,
        apiCall: suspend () -> T,
        unsuccessfulRetriesCount: Int = 0
    ): Flow<ResultWrapper<T>> where T : ServerResponseData {
        return withContext(dispatcher) {
            flow {
                try {
                    emit(ResultWrapper.Loading())

                    val apiResult = apiCall.invoke()

                    if (apiResult.isSuccessful) {
                        emit(ResultWrapper.Success(apiResult))
                    } else {
                        emit(
                            ResultWrapper.ApiError(
                                apiResult.error,
                                apiResult.errorMessage,
                                apiResult
                            )
                        )
                    }
                } catch (throwable: Throwable) {
                    throwable.printStackTrace()
                    doRetryCall(
                        dispatcher,
                        apiCall,
                        null,
                        unsuccessfulRetriesCount
                    )
                }
            }
        }
    }

    private suspend fun <T> doRetryCall(
        dispatcher: CoroutineDispatcher,
        apiCall: suspend () -> T,
        apiResult: T?,
        unsuccessfulRetriesCount: Int = 0
    ): Flow<ResultWrapper<T>> where T : ServerResponseData = flow {
        if (unsuccessfulRetriesCount < MAX_RETRY_COUNT) {
            executeApiCall(dispatcher, apiCall, unsuccessfulRetriesCount + 1)
        } else {
            emit(
                ResultWrapper.ApiError(
                    apiResult?.error,
                    apiResult?.errorMessage,
                    apiResult
                )
            )
        }
    }
}