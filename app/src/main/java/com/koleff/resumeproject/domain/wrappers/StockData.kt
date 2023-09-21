package com.koleff.resumeproject.domain.wrappers

import com.koleff.resumeproject.data.remote.dto.StockDataDto

//Used for presentation layer creation.
data class StockData (val stockDataDto: StockDataDto){
    val date = stockDataDto.date
    val stockTag = stockDataDto.stockTag
    val exchangeTag = stockDataDto.exchangeTag
    val openPrice = stockDataDto.openPrice
    val closePrice = stockDataDto.closePrice
    val highPrice = stockDataDto.highPrice
    val lowPrice= stockDataDto.lowPrice
    val volume = stockDataDto.volume
    val adjustOpenPrice = stockDataDto.adjustOpenPrice
    val adjustClosePrice = stockDataDto.adjustClosePrice
    val adjustHighPrice = stockDataDto.adjustHighPrice
    val adjustLowPrice = stockDataDto.adjustLowPrice
    val adjustVolume = stockDataDto.adjustVolume
}
