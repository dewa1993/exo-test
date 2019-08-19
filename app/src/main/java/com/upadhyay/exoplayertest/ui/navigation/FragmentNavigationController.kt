package com.upadhyay.exoplayertest.ui.navigation

import com.upadhyay.exoplayertest.R
import com.upadhyay.exoplayertest.ui.activity.MainActivity
import com.upadhyay.exoplayertest.ui.fragment.HomeFragment
import com.upadhyay.exoplayertest.ui.fragment.PlayerFragment
import javax.inject.Inject

class FragmentNavigationController @Inject constructor(dashboardActivity: MainActivity) :
    AbstractNavigationController(dashboardActivity) {

    override fun provideContainerId() = R.id.fragment_container


    fun startPlayerFragment() {
        changeFragment(
            PlayerFragment.getInstance(),
            isBackNavigation = true
        )
    }

    fun navigateToHomeFragment() {
        changeFragment(
            HomeFragment.getInstance(),
            isBackNavigation = false
        )
    }

}