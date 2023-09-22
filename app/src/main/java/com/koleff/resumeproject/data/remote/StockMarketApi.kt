package com.koleff.resumeproject.data.remote

import com.koleff.resumeproject.data.remote.dto.TickerDto
import com.koleff.resumeproject.domain.models.responses.GetStockDataResponse
import com.koleff.resumeproject.domain.models.responses.GetTickerResponse
import com.koleff.resumeproject.domain.models.responses.GetTickersResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface StockMarketApi {

    @GET("v1/eod")
    suspend fun getStockData(
        @Query("symbols") symbols: String,
        @Query("date_from") dateFrom: String,
        @Query("date_to") dateTo: String,
    ): GetStockDataResponse

    @GET("v1/tickers")
    suspend fun getTickers(): GetTickersResponse

    @GET("v1/tickers/{stockTag}")
    suspend fun getTicker(@Path("stockTag") stockTag: String): GetTickerResponse
}


