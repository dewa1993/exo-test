package com.upadhyay.exoplayertest.ui.fragment

import android.os.Bundle
import android.view.View
import com.upadhyay.exoplayertest.R
import com.upadhyay.exoplayertest.databinding.FragmentHomeBinding
import com.upadhyay.exoplayertest.ui.activity.MainActivity
import com.upadhyay.exoplayertest.ui.navigation.FragmentNavigationController

class HomeFragment : AbstractBaseDataBindingFragment<FragmentHomeBinding>() {

    companion object {
        fun getInstance() = HomeFragment()
    }

    override fun getLayout(): Int = R.layout.fragment_home

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navigationController = FragmentNavigationController(activity!! as MainActivity)

        binding.btnStart.setOnClickListener {
            navigationController.startPlayerFragment()
        }
    }

}


