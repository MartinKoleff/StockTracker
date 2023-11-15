package com.koleff.resumeproject.presentation.state

import com.koleff.resumeproject.domain.wrappers.StockData
import com.koleff.resumeproject.domain.wrappers.networkWrappers.KoleffError

data class TickersState(
    val tickersList: List<StockData> = emptyList(),
    val isSuccessful: Boolean = false,
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val error : KoleffError = KoleffError.GENERIC
)