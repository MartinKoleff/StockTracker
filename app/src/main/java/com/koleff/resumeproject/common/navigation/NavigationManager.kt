package com.koleff.resumeproject.common.navigation

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.koleff.resumeproject.presentation.fragments.DashboardFragment
import com.koleff.resumeproject.presentation.fragments.DashboardFragmentDirections
import com.koleff.resumeproject.presentation.fragments.FavouritesFragment
import com.koleff.resumeproject.presentation.fragments.FavouritesFragmentDirections
import javax.inject.Inject


/**
 * AppCompatActivity allows use of supportFragmentManager.
 * To use BaseActivity l need the current activity binding.
 * BaseActivity extends AppCompatActivity.
 */

class NavigationManager @Inject constructor(
    private val activity: AppCompatActivity,
    private val containerId: Int,
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

    private fun getActiveFragment(): Fragment? {
        supportFragmentManager.fragments.forEach {
            if (it.isVisible) return it
        }

        return null
    }

    @Deprecated("Old way")
    fun showFragment(type: FragmentType) {
        val fragment = when (type) {
            FragmentType.DASHBOARD -> dashboardFragment
            FragmentType.FAVOURITES -> favouritesFragment
        }

        val attachedFragment = supportFragmentManager.findFragmentByTag(type.title)

        supportFragmentManager.beginTransaction().apply {
            //Selected fragment already shown...
            if (attachedFragment?.isAdded == true) {
                show(attachedFragment)
            } else {
                containerId.apply { add(this, fragment, type.title) }
            }
        }.commitNowAllowingStateLoss()
    }
}