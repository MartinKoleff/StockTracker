package com.koleff.resumeproject.presentation.activities

import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.ListView
import androidx.core.view.GravityCompat
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.koleff.resumeproject.R
import com.koleff.resumeproject.common.navigation.FragmentType
import com.koleff.resumeproject.common.navigation.NavigationManager
import com.koleff.resumeproject.databinding.ActivityMainBinding
import com.koleff.resumeproject.presentation.activities.base.BaseActivity
import com.koleff.resumeproject.presentation.adapters.AdapterNavigationSettings
import com.koleff.resumeproject.presentation.adapters.SettingItem
import com.koleff.resumeproject.presentation.fragments.DashboardFragment
import com.koleff.resumeproject.presentation.fragments.FavouritesFragment
import com.koleff.resumeproject.presentation.fragments.MainFragmentFeatures
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    override val bindingInflater: (LayoutInflater) -> ActivityMainBinding =
        ActivityMainBinding::inflate

    private val navigationManager =
        NavigationManager(this@MainActivity, R.id.container_fragment, supportFragmentManager)

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
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation_view)
        bottomNavigationView.background = null
        bottomNavigationView.menu.getItem(1).isEnabled = false

        //Bottom buttons navigation
        bottomNavigationView.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.dashboardFragmentMenu -> {
                    navigationManager.showFragment(FragmentType.DASHBOARD)
                    true
                }

                R.id.favouritesFragmentMenu -> {
                    navigationManager.showFragment(FragmentType.FAVOURITES)
                    true
                }
                else -> false
            }
        }

        //Initial load
        navigationManager.showFragment(FragmentType.DASHBOARD)

        //Refresh
        val refreshButton = findViewById<ImageView>(R.id.ivRefresh)
        refreshButton.setOnClickListener {
            val currentFragment =
                supportFragmentManager.findFragmentById(R.id.container_fragment) as MainFragmentFeatures

            currentFragment.refresh()
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