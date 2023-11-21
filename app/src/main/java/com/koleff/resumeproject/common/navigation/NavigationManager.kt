package com.koleff.resumeproject.common.navigation

import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.koleff.resumeproject.presentation.fragments.DashboardFragment
import com.koleff.resumeproject.presentation.fragments.FavouritesFragment


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

    //Fragments
    private val dashboardFragment: DashboardFragment by lazy {
        DashboardFragment()
    }

    private val favouritesFragment: FavouritesFragment by lazy {
        FavouritesFragment()
    }


    private fun getActiveFragment(): Fragment? {
        supportFragmentManager.fragments.forEach {
            if (it.isVisible) return it
        }

        return null
    }

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