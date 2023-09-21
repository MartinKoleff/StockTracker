package com.koleff.resumeproject.data.remote

import com.koleff.resumeproject.domain.models.responses.GetStockDataResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface StockMarketApi {

    @GET("v1/eod")
    suspend fun getStockData(
        @Query("symbols") symbols: String,
        @Query("date_from") dateFrom: String,
        @Query("date_to") dateTo: String,
//        @Body body: GetStockDataBody
    ): GetStockDataResponse
}


