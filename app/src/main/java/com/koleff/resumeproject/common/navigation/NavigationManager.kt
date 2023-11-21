package com.koleff.resumeproject.common.navigation

import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.fragment.NavHostFragment
import com.koleff.resumeproject.presentation.fragments.DashboardFragment
import com.koleff.resumeproject.presentation.fragments.DashboardFragmentDirections
import com.koleff.resumeproject.presentation.fragments.FavouritesFragment
import com.koleff.resumeproject.presentation.fragments.FavouritesFragmentDirections


/**
 * AppCompatActivity allows use of supportFragmentManager.
 * To use BaseActivity l need the current activity binding.
 * BaseActivity extends AppCompatActivity.
 */

class NavigationManager(
    private val activity: AppCompatActivity,
    @IdRes private val containerId: Int,
    private val supportFragmentManager: FragmentManager = activity.supportFragmentManager
) {
    private val navHostFragment by lazy {
        supportFragmentManager.findFragmentById(containerId) as NavHostFragment
    }
    private val navController by lazy { navHostFragment.navController }

    //Fragments
    private val dashboardFragment: DashboardFragment by lazy {
        DashboardFragment()
    }

    private val favouritesFragment: FavouritesFragment by lazy {
        FavouritesFragment()
    }

    fun navigate(type: FragmentType){
        when (type) {
            FragmentType.DASHBOARD -> {
                val action =
                    FavouritesFragmentDirections.actionFavouritesFragmentToDashboardFragment()

                navController.navigate(action)
            }
            FragmentType.FAVOURITES -> {
                val action = DashboardFragmentDirections.actionDashboardFragmentToFavouritesFragment()

                navController.navigate(action)
            }
        }
    }

    fun getActiveFragment(): Fragment? {
        supportFragmentManager.fragments.forEach {
            if (it.isVisible) {
                if(it is NavHostFragment){
                    return it.childFragmentManager.fragments.first()
                }
                return it
            }
        }

        return null
    }
}