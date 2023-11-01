package com.koleff.resumeproject.presentation.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.koleff.resumeproject.KoleffApp
import com.koleff.resumeproject.common.errorHandling.ErrorHandler
import com.koleff.resumeproject.common.managers.DataManager
import com.koleff.resumeproject.data.dto.StockDto
import com.koleff.resumeproject.domain.repositories.StockRepository
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
class StocksViewModel @Inject constructor(
    private val stockRepository: StockRepository,
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
//        _state.stateIn(
//            initialValue = emptyList(),
//            scope = viewModelScope,
//            started = SharingStarted.WhileSubscribed(5000)
//        )

//        getStockData("AAPL", "2023-09-10", "2023-09-18")
    }

    fun getStocksData() {
        viewModelScope.launch(dispatcher) {
            stockRepository.getStocks().onEach { apiResult ->
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
                            apiResult.data.stockData
                                .map(StockDto::toStockData)
                                .also {
                                Log.d(KoleffApp.TAG_LOG, "Flow received in StocksViewModel.")
                                _state.value = it
                            }
                    }
                }
            }.collect()
        }
    }
}
