package com.koleff.resumeproject.presentation.fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import com.koleff.resumeproject.databinding.FragmentDashboardBinding
import com.koleff.resumeproject.databinding.FragmentFavouritesBinding
import com.koleff.resumeproject.presentation.fragments.base.BaseFragment

class FavouritesFragment: BaseFragment<FragmentFavouritesBinding>() {


    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentFavouritesBinding =
        FragmentFavouritesBinding::inflate

    override fun setup() {
        TODO("Not yet implemented")
    }
}