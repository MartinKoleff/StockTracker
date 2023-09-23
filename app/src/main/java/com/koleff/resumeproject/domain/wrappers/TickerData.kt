package com.koleff.resumeproject.domain.wrappers

import com.koleff.resumeproject.data.remote.dto.StockExchange
import com.koleff.resumeproject.data.remote.dto.TickerDto

data class TickerData (val tickerDto: TickerDto){
    val country: String? = tickerDto.country
    val hasEod: Boolean = tickerDto.hasEod
    val hasIntraday: Boolean = tickerDto.hasIntraday
    val name: String = tickerDto.name
    val stockExchange: StockExchange = tickerDto.stockExchange
    val stockTag: String = tickerDto.stockTag
}

