package com.upadhyay.exoplayertest.utils

import android.view.View
import androidx.databinding.BindingAdapter


@BindingAdapter("android:animatedVisibility")
fun setAnimatedVisibility(target: View, isVisible: Boolean) {
    target.visibility = if (isVisible) View.VISIBLE else View.GONE
}