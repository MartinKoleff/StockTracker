package com.koleff.resumeproject.data.remote

import com.koleff.resumeproject.domain.models.requests.GetStocksRequest
import com.koleff.resumeproject.domain.models.requests.SaveStockRequest
import com.koleff.resumeproject.domain.models.requests.SaveStocksRequest
import com.koleff.resumeproject.domain.models.responses.GetStockResponse
import com.koleff.resumeproject.domain.models.responses.GetStocksResponse
import com.koleff.resumeproject.domain.models.responses.baseResponse.BaseResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Path


interface StockApi {
    @GET("api/v1/stock/get/all")
    suspend fun getStocks(): GetStocksResponse

    @GET("api/v1/stock/get/{stock_tag}")
    suspend fun getStock(
        @Path("stockTag") stockTag: String,
        @Body body: GetStocksRequest
    ): GetStockResponse

    @GET("api/v1/stock/save/all")
    suspend fun saveStocks(
        @Body body: SaveStocksRequest
    ): BaseResponse

    @GET("api/v1/stock/save/{stock_tag}")
    suspend fun saveStock(
        @Path("stockTag") stockTag: String,
        @Body body: SaveStockRequest
    ): BaseResponse
}


