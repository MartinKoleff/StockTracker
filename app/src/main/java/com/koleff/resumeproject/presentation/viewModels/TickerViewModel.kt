package com.koleff.resumeproject.presentation.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.koleff.resumeproject.KoleffApp
import com.koleff.resumeproject.common.errorHandling.ErrorHandler
import com.koleff.resumeproject.common.managers.DataManager
import com.koleff.resumeproject.domain.repositories.StockMarketRepository
import com.koleff.resumeproject.domain.wrappers.TickerData
import com.koleff.resumeproject.domain.wrappers.networkWrappers.ResultWrapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TickerViewModel @Inject constructor(
    private val stockMarketRepository: StockMarketRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Main
) : ViewModel() {

    private val _state: MutableStateFlow<TickerData>? = null
    val state: StateFlow<TickerData>?
        get() = _state


    init {
        getTicker("AAPL")
    }

    private fun getTicker(stockTag: String) {
        viewModelScope.launch(dispatcher) {
            stockMarketRepository.getTicker(stockTag).collect { apiResult ->
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
                        DataManager.selectedTicker = apiResult.data.ticker.also {
                            Log.d(KoleffApp.TAG_LOG, "Flow received in TickerViewModel.")

                            _state?.value = it
                        }
                    }
                }
            }
        }
    }
}