package com.koleff.resumeproject.domain.wrappers

import com.koleff.resumeproject.data.dto.EndOfDayDto
import com.koleff.resumeproject.data.dto.IntraDayDto
import com.koleff.resumeproject.data.dto.StockDto
import java.util.Date

//Used for presentation layer creation.
data class StockData (val stockDto: StockDto){
    private val currentDateIntraDay: IntraDayDto? = stockDto.intraDayList.find { it.date == Date() }
    private val currentDateEndOfDay: EndOfDayDto? = stockDto.endOfDayList.find { it.date == Date() }
    val stockTag = stockDto.tag
    val exchangeTag = stockDto.stockExchange.name
    val openPrice = currentDateIntraDay?.open ?: 0.0
    val closePrice = currentDateIntraDay?.close ?: 0.0
    val highPrice = currentDateIntraDay?.high ?: 0.0
    val lowPrice = currentDateIntraDay?.low ?: 0.0
    val volume = currentDateIntraDay?.volume ?: 0.0
    val adjustOpenPrice = currentDateEndOfDay?.adjustOpen ?: 0.0
    val adjustClosePrice = currentDateEndOfDay?.adjustClose ?: 0.0
    val adjustHighPrice = currentDateEndOfDay?.adjustHigh ?: 0.0
    val adjustLowPrice = currentDateEndOfDay?.adjustLow ?: 0.0
    val adjustVolume = currentDateEndOfDay?.adjustVolume ?: 0.0
    val splitFactor = currentDateEndOfDay?.splitFactor ?: 0.0
    val dividend = currentDateEndOfDay?.dividend ?: 0.0
    val isRising: Boolean = openPrice - closePrice > 0
    val dayDifference = openPrice - closePrice
    val changePercent = openPrice / dayDifference * 100
    val date = Date()
}
