package com.koleff.resumeproject.presentation.activities

import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.ListView
import androidx.core.view.GravityCompat
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.koleff.resumeproject.R
import com.koleff.resumeproject.common.navigation.FragmentType
import com.koleff.resumeproject.common.navigation.NavigationManager
import com.koleff.resumeproject.databinding.ActivityMainBinding
import com.koleff.resumeproject.domain.apiServices.StockMarketApiService
import com.koleff.resumeproject.presentation.activities.adapters.AdapterNavigationSettings
import com.koleff.resumeproject.presentation.activities.adapters.SettingItem
import com.koleff.resumeproject.presentation.activities.base.BaseActivity
import com.koleff.resumeproject.presentation.fragments.DashboardFragmentDirections
import com.koleff.resumeproject.presentation.fragments.FavouritesFragmentDirections
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    override val bindingInflater: (LayoutInflater) -> ActivityMainBinding =
        ActivityMainBinding::inflate

    @Inject
    lateinit var stockMarketApiService: StockMarketApiService

    //Navigation
    private val navigationManager: NavigationManager by lazy {
        NavigationManager(
            this@MainActivity,
            R.id.container_fragment
        )
    }

    //Buttons
    private val dashboardButton: View by lazy { findViewById(R.id.button_dashboard) }
    private val favouritesButton: View by lazy { findViewById(R.id.button_favourites) }
    private val searchButton: FloatingActionButton by lazy { findViewById(R.id.button_search) }
    private var selectedButton: View? = null
        set(value) {
            if (value == field) return

            field = value

            //Button selection
            dashboardButton.isSelected = value == dashboardButton
            favouritesButton.isSelected = value == favouritesButton
        }

    override fun setup(): Unit = with(binding) {

        //Navigation drawer
        containerMain.toolbar.ivNavigationDrawer.setOnClickListener {
            if (dlNavigation.isDrawerOpen(navigationDrawer)) {
                dlNavigation.closeDrawer(GravityCompat.START)
            } else {
                dlNavigation.openDrawer(GravityCompat.START)
            }
        }

        //Navigation settings list
        val listSettings = findViewById<ListView>(R.id.list_settings)

        val navigationSettings = mutableListOf<SettingItem>()
        fillNavigationSettings(navigationSettings)

        val adapterSettings = AdapterNavigationSettings(this@MainActivity, navigationSettings)
        listSettings.adapter = adapterSettings

        //Bottom navigation bar
        containerMain.bottomNavigationBar.bottomNavigationView.background = null
        containerMain.bottomNavigationBar.bottomNavigationView.menu.getItem(1).isEnabled = false

        //Bottom navigation bar buttons
        dashboardButton.setOnClickListener {
            navigationManager.navigate(FragmentType.DASHBOARD)
            selectedButton = it
        }

        favouritesButton.setOnClickListener {
            navigationManager.navigate(FragmentType.FAVOURITES)
            selectedButton = it
        }

        //Load dashboard
        navigationManager.showFragment(FragmentType.DASHBOARD)
        selectedButton = dashboardButton

//        callRequests()
    }

    private fun callRequests() {
        lifecycleScope.launch {
//            stockMarketApiService.getStockData("AAPL", "2023-09-10", "2023-09-18")
//            stockMarketApiService.getTickers()
//            stockMarketApiService.getTicker("AAPL")
        }
    }

    private fun fillNavigationSettings(list: MutableList<SettingItem>) {
        list.add(SettingItem(SettingItem.id_language, resources.getString(R.string.text_language), R.drawable.ic_language))
//        list.add(SettingItem(SettingItem.id_password, resources.getString(R.string.text_change_password), R.drawable.ic_pass))
//        list.add(SettingItem(SettingItem.id_email, resources.getString(R.string.text_password_recovery_email), R.drawable.ic_email))
//        list.add(SettingItem(SettingItem.id_logout, resources.getString(R.string.text_logout), R.drawable.ic_logout))
//        list.add(SettingItem(SettingItem.id_biometrics, resources.getString(R.string.text_enable_biometrics), R.drawable.ic_vector_faceid))
//        list.add(SettingItem(SettingItem.id_notifications, resources.getString(R.string.text_notifications), R.drawable.ic_notifications))
//        list.add(SettingItem(SettingItem.id_night_mode, resources.getString(R.string.text_night_mode), R.drawable.ic_night_mode))
    }
}