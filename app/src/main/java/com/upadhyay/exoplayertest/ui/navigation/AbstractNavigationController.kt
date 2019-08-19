package com.upadhyay.exoplayertest.ui.navigation

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

abstract class AbstractNavigationController constructor(var appCompatActivity: AppCompatActivity) {

    protected abstract fun provideContainerId(): Int

    protected fun changeFragment(
        fragment: Fragment,
        isBackNavigation: Boolean
    ) {
        appCompatActivity.replaceFragment(
            containerId = provideContainerId(),
            fragment = fragment,
            isBackStack = isBackNavigation
        )
    }

    fun popBackStackImmediate() {
        appCompatActivity.popBackStackImmediate()
    }


}