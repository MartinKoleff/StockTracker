package com.koleff.resumeproject.common.utils

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.koleff.resumeproject.data.dto.CurrencyDto
import com.koleff.resumeproject.data.dto.EndOfDayDto
import com.koleff.resumeproject.data.dto.IntraDayDto
import com.koleff.resumeproject.data.dto.StockDto
import com.koleff.resumeproject.data.dto.StockExchangeDto
import com.koleff.resumeproject.data.dto.TimezoneDto
import com.koleff.resumeproject.domain.wrappers.StockData
import java.util.Date

object MockupDataGenerator {

    fun getTickers(): List<StockData> {
        val stockExchange = StockExchangeDto(
            name = "Mock Stock Exchange",
            acronym = "MSE",
            country = "Mockland",
            countryCode = "ML",
            city = "Mock City",
            website = "http://mockexchange.com",
            timezone = TimezoneDto(
                timezone = "UTC",
                abbreviation = "GMT",
                abbreviationDst = "GMT+1"
            ),
            currency = CurrencyDto(
                code = "USD",
                symbol = "$",
                name = "US Dollar"
            )
        )

        val endOfDay = EndOfDayDto(
            open = 100.0,
            close = 110.0,
            high = 120.0,
            low = 90.0,
            volume = 10000.0,
            adjustOpen = 95.0,
            adjustClose = 105.0,
            adjustHigh = 115.0,
            adjustLow = 85.0,
            adjustVolume = 9500.0,
            splitFactor = 1.5,
            dividend = 2.0,
            date = Date()
        )

        val intraDay = IntraDayDto(
            open = 120.0,
            close = 115.0,
            high = 125.0,
            low = 110.0,
            last = 118.0,
            volume = 5000.0,
            date = Date()
        )

        val stockDto = StockDto(
            name = "Mock Stock",
            tag = "MCK",
            hasIntraDay = true,
            hasEndOfDay = true,
            endOfDayList = listOf(endOfDay),
            intraDayList = listOf(intraDay),
            stockExchange = stockExchange
        )

        val stockDto2 = StockDto(
            name = "Mock Stock 2",
            tag = "MCK2",
            hasIntraDay = true,
            hasEndOfDay = true,
            endOfDayList = listOf(endOfDay),
            intraDayList = listOf(intraDay),
            stockExchange = stockExchange
        )

        val stockData = stockDto.toStockData()
        val stockData2 = stockDto2.toStockData()

        return listOf(stockData, stockData2)
    }
}
