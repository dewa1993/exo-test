package com.upadhyay.exoplayertest.ui.activity

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.upadhyay.exoplayertest.R
import com.upadhyay.exoplayertest.ui.navigation.FragmentNavigationController
import javax.inject.Inject

class MainActivity : AppCompatActivity(){


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navigationController = FragmentNavigationController(this)

        navigationController.navigateToHomeFragment()
    }

}