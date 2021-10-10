package com.gemidroid.comicshow.presentation.ui

import android.view.View
import com.google.android.material.snackbar.Snackbar

fun View.showShortSnackBar(msg: String) {
    Snackbar.make(this, msg, Snackbar.LENGTH_SHORT).show()
}
