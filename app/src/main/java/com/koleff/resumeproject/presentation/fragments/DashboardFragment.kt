package com.koleff.resumeproject.presentation.fragments

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.koleff.resumeproject.KoleffApp
import com.koleff.resumeproject.databinding.FragmentDashboardBinding
import com.koleff.resumeproject.domain.wrappers.StockData
import com.koleff.resumeproject.presentation.adapters.AdapterDashboard
import com.koleff.resumeproject.presentation.fragments.base.BaseFragment
import com.koleff.resumeproject.presentation.viewModels.StocksViewModel
import kotlinx.coroutines.launch

class DashboardFragment : BaseFragment<FragmentDashboardBinding>(), MainFragment {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentDashboardBinding =
        FragmentDashboardBinding::inflate

    private val stocksViewModel: StocksViewModel by viewModels()

    override fun setup(): Unit = with(binding) {
        var stocksList = listOf<StockData>()
        val stockAdapter = AdapterDashboard(stocksList)
        rvStocks.adapter = stockAdapter

        //ViewModels
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                stocksViewModel.state.collect {
                    stocksList = stocksViewModel.state.value.tickersList

                    Log.d(
                        KoleffApp.TAG_LOG,
                        "Flow received in MainActivity from StocksViewModel -> $it"
                    )
                }
            }
        }
    }

    override fun refresh() {
//        stocksViewModel.getStocksData("")
        Log.d(
            KoleffApp.TAG_LOG,
            "DASHBOARD REFRESH"
        )
    }
}