package com.koleff.resumeproject.presentation.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.koleff.resumeproject.KoleffApp
import com.koleff.resumeproject.common.errorHandling.ErrorHandler
import com.koleff.resumeproject.common.managers.DataManager
import com.koleff.resumeproject.data.remote.dto.StockDataDto
import com.koleff.resumeproject.domain.repositories.StockMarketRepository
import com.koleff.resumeproject.domain.wrappers.StockData
import com.koleff.resumeproject.domain.wrappers.networkWrappers.ResultWrapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StockMarketViewModel @Inject constructor(
    private val stockMarketRepository: StockMarketRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Main
) : ViewModel() {

    private val _state: MutableStateFlow<List<StockData>> =
 MutableStateFlow(emptyList())
    val state: StateFlow<List<StockData>>
        get() = _state

    override fun onCleared() {
        super.onCleared()
    }

    init {
        getStockData("AAPL", "2023-09-10", "2023-09-18")
    }

    fun getStockData(stockTag: String, dateFrom: String, dateTo: String) {
        viewModelScope.launch(dispatcher) {
            stockMarketRepository.getStockData(stockTag, dateFrom, dateTo).onEach { apiResult ->
                when (apiResult) {
                    is ResultWrapper.ApiError -> {
                        ErrorHandler.showError(
                            apiResult.error,
                            apiResult.errorMessage
                        )
                    }

                    is ResultWrapper.Loading -> {

                    }

                    is ResultWrapper.Success -> {
                        DataManager.stocks =
                            apiResult.data.stockData.map(StockDataDto::toStockData).also {
                                Log.d(KoleffApp.TAG_LOG, "Flow received in StockMarketViewModel.")
                                _state.value = it
                            }
                    }
                }
            }.collect()
        }
    }
}
