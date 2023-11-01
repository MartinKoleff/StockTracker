package com.koleff.resumeproject.domain.wrappers

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
    val splitFactor = stockDataDto.splitFactor
    val dividend = stockDataDto.dividend
    val isRising: Boolean = stockDataDto.openPrice - stockDataDto.closePrice > 0
    val dayDifference: Double = stockDataDto.openPrice - stockDataDto.closePrice
    val changePercent: Double = stockDataDto.openPrice / dayDifference * 100
}
