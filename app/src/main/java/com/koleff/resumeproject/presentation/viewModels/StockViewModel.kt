package com.koleff.resumeproject.presentation.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.koleff.resumeproject.KoleffApp
import com.koleff.resumeproject.common.errorHandling.ErrorHandler
import com.koleff.resumeproject.common.managers.DataManager
import com.koleff.resumeproject.domain.repositories.StockRepository
import com.koleff.resumeproject.domain.wrappers.StockData
import com.koleff.resumeproject.domain.wrappers.networkWrappers.ResultWrapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StockViewModel @Inject constructor(
    private val stockMarketRepository: StockRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Main
) : ViewModel() {

    private val _state: MutableStateFlow<StockData>? = null
    val state: StateFlow<StockData>?
        get() = _state


    init {
//        getTicker("AAPL")
    }

    private fun getStock(
        stockTag: String,
        dateFrom: String? = null,
        dateTo: String? = null
    ) {
        viewModelScope.launch(dispatcher) {
            stockMarketRepository.getStock(stockTag, dateFrom, dateTo).collect { apiResult ->
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
                        DataManager.selectedStock = apiResult.data.stock
                            .toStockData()
                            .also {
                                Log.d(KoleffApp.TAG_LOG, "Flow received in StockViewModel.")

                                _state?.value = it
                            }
                    }
                }
            }
        }
    }
}