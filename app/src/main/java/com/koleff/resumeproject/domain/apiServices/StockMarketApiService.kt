package com.koleff.resumeproject.domain.apiServices

import com.koleff.resumeproject.domain.apiServices.repositories.interfaces.StockMarketRepository
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
object StockMarketApiService {
    //Inject repo impl

    @Inject
    lateinit var stockMarketRepository: StockMarketRepository

    suspend fun getStockData(
        stockTag: String,
        dateFrom: String,
        dateTo: String
    ) {
        stockMarketRepository.getStockData(stockTag, dateFrom, dateTo)

        //Handle response...
    }
}