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
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TickersViewModel @Inject constructor(
    private val stockMarketRepository: StockMarketRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Main
) : ViewModel() {

    private val _state: MutableStateFlow<List<TickerData>> =
        MutableStateFlow(emptyList())
    val state: StateFlow<List<TickerData>>
        get() = _state


    init {
        getTickers()
    }

    private fun getTickers() {
        viewModelScope.launch(dispatcher) {
            stockMarketRepository.getTickers().onEach { apiResult ->
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
                        DataManager.tickers =
                            apiResult.data.tickers.map(TickerDto::toTickerData).also {
                                Log.d(KoleffApp.TAG_LOG, "Flow received in TickersViewModel.")

                                _state.value = it
                            }
                    }
                }
            }.collect()
        }
    }
}
