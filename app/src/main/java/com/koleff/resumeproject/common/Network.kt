package com.koleff.resumeproject.common

import com.koleff.resumeproject.domain.wrappers.networkWrappers.ResultWrapper
import com.koleff.resumeproject.domain.wrappers.networkWrappers.ServerResponseData
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

object Network {
    private const val MAX_RETRY_COUNT = 1

    suspend fun <T> executeApiCall(
        dispatcher: CoroutineDispatcher,
        apiCall: suspend () -> T,
        unsuccessfulRetriesCount: Int = 0
    ): ResultWrapper<T> where T : ServerResponseData {
        return withContext(dispatcher) {
            try {
                val apiResult = apiCall.invoke()

                if (apiResult.isSuccessful) {
                    ResultWrapper.Success(apiResult)
                } else {
                    ResultWrapper.KoleffError(
                        apiResult.error,
                        apiResult.errorMessage,
                        apiResult
                    )
                }
            }catch (throwable: Throwable) {
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

    private suspend fun <T> doRetryCall(
        dispatcher: CoroutineDispatcher,
        apiCall: suspend () -> T,
        apiResult: T?,
        unsuccessfulRetriesCount: Int = 0
    ): ResultWrapper<T> where T : ServerResponseData {

        return if (unsuccessfulRetriesCount < MAX_RETRY_COUNT) {
            executeApiCall(dispatcher, apiCall, unsuccessfulRetriesCount + 1)
        } else {
            ResultWrapper.KoleffError(
                apiResult?.error,
                apiResult?.errorMessage,
                apiResult
            )
        }
    }
}