package com.teacomputers.moviesapp.common

import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.teacomputers.moviesapp.ViewsManager

fun Fragment.toast(message: String) {
    Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
}

fun View.snackBar(message: String, duration: Int = Snackbar.LENGTH_LONG) {
    Snackbar.make(this, message, duration).show()
}

fun View.gone() = run { visibility = View.GONE }

fun View.visible() = run { visibility = View.VISIBLE }

fun View.invisible() = run { visibility = View.INVISIBLE }

fun View.getStringText(stringResId: Int): String = resources.getString(stringResId)

fun Fragment.showLoading() {
    (requireActivity() as ViewsManager).showLoading()
}

fun Fragment.hideLoading() {
    (requireActivity() as ViewsManager).hideLoading()
}
