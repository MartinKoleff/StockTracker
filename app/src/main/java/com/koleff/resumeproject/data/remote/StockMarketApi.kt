package com.koleff.resumeproject.data.remote

import com.koleff.resumeproject.domain.apiServices.models.requests.GetStockDataBody
import com.koleff.resumeproject.domain.models.responses.GetStockDataResponse
import retrofit2.http.Body
import retrofit2.http.GET

interface StockMarketApi {

    @GET("v1/eod")
    suspend fun getStockData(
        @Body body: GetStockDataBody
    ): GetStockDataResponse
}


