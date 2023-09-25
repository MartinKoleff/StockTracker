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
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StockMarketViewModel @Inject constructor(
    private val stockMarketRepository: StockMarketRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Main
) : ViewModel() {

    private var _state: Flow<List<StockData>> = flowOf<List<StockData>>()
    val state: Flow<List<StockData>> = _state

    init {
        getStockData("AAPL", "2023-09-10", "2023-09-18")
    }

    private fun getStockData(stockTag: String, dateFrom: String, dateTo: String) {
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
                                Log.d(KoleffApp.TAG_LOG, it.toString())

                                _state = flowOf(it)
                            }
                    }
                }
            }.collect()
        }
    }
}
