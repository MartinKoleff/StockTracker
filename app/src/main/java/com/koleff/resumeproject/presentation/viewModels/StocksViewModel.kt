package com.koleff.resumeproject.presentation.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.koleff.resumeproject.KoleffApp
import com.koleff.resumeproject.data.dto.StockDto
import com.koleff.resumeproject.domain.repositories.StockRepository
import com.koleff.resumeproject.domain.wrappers.TickersState
import com.koleff.resumeproject.domain.wrappers.networkWrappers.KoleffError
import com.koleff.resumeproject.domain.wrappers.networkWrappers.ResultWrapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StocksViewModel @Inject constructor(
    private val stockRepository: StockRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Main
) : ViewModel() {

    private val _state: MutableStateFlow<TickersState> = MutableStateFlow(TickersState())
    val state: StateFlow<TickersState>
        get() = _state

    public fun getStocksData(
        stockTag: String,
        dateFrom: String? = null,
        dateTo: String? = null
    ) {
        viewModelScope.launch(dispatcher) {
            stockRepository.getStocks().collect { apiResult ->
                when (apiResult) {
                    is ResultWrapper.ApiError -> {
                        _state.value = TickersState(
                            isError = true,
                            error = apiResult.error ?: KoleffError.GENERIC
                        )
                    }

                    is ResultWrapper.Loading -> {
                        _state.value = TickersState(isLoading = true)
                    }

                    is ResultWrapper.Success -> {
                        Log.d(KoleffApp.TAG_LOG, "Flow received in StocksViewModel.")
                        
                        _state.value = TickersState(
                            isSuccessful = true,
                            tickersList = apiResult.data.stockData
                                .map(StockDto::toStockData)
                        )
                    }
                }
            }
        }
    }
}