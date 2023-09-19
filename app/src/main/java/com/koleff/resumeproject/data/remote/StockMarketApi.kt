package com.koleff.resumeproject.data.remote.dto

import com.koleff.resumeproject.domain.api.models.requests.GetStockDataBody
import com.koleff.resumeproject.domain.api.models.responses.GetStockDataResponse
import retrofit2.http.Body
import retrofit2.http.GET

interface StockMarketApi {

    @GET("v1/eod")
    suspend fun getStockData(
        @Body body: GetStockDataBody
    ): GetStockDataResponse
}


