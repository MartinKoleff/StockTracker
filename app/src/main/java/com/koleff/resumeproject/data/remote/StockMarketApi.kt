package com.koleff.resumeproject.data.remote

import com.koleff.resumeproject.common.Constants
import com.koleff.resumeproject.domain.models.requests.GetStockDataBody
import com.koleff.resumeproject.domain.models.responses.GetStockDataResponse
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query

interface StockMarketApi {

    @POST("v1/eod")
    suspend fun getStockData(
        @Query("symbols") symbols: String,
        @Query("date_from") dateFrom: String,
        @Query("date_to") dateTo: String,
//        @Body body: GetStockDataBody
    ): GetStockDataResponse
}


