package com.koleff.resumeproject.presentation.fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import com.koleff.resumeproject.databinding.FragmentDashboardBinding
import com.koleff.resumeproject.presentation.fragments.base.BaseFragment

class DashboardFragment: BaseFragment<FragmentDashboardBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentDashboardBinding =
        FragmentDashboardBinding::inflate

    override fun setup() {

    }
}