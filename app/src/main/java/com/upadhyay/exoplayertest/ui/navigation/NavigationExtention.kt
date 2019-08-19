package com.upadhyay.exoplayertest.ui.navigation


import androidx.annotation.IdRes
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.upadhyay.exoplayertest.R


fun AppCompatActivity.replaceFragment(containerId: Int, fragment: Fragment, isBackStack: Boolean) {
    supportFragmentManager.transact(fragment::class.simpleName, isBackStack = isBackStack) {
        replace(containerId, fragment)
    }
}

private inline fun FragmentManager.transact(
    fragmentName: String?,
    isBackStack: Boolean,
    action: FragmentTransaction.() -> Unit
) {
    beginTransaction().apply {
        action()
        if (isBackStack)
            addToBackStack(fragmentName)
    }.commit()
}

fun AppCompatActivity.popBackStackImmediate() {
    supportFragmentManager.popBackStackImmediate()
}




