package com.koleff.resumeproject.api.services.interfaces

import com.koleff.resumeproject.api.models.requests.GetStockDataBody
import com.koleff.resumeproject.api.models.responses.GetStockDataResponse
import retrofit2.http.Body
import retrofit2.http.GET

interface StockMarketApiService {

    @GET("v1/eod")
    suspend fun getStockData(
        @Body body: GetStockDataBody
    ): GetStockDataResponse
}


