package com.gemidroid.comicshow.presentation.ui.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.gemidroid.comicshow.R
import kotlinx.android.synthetic.main.loading_view.*

class ComicsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.comics_activity)
    }

    fun showLoading(){
        progress.visibility = View.VISIBLE
    }

    fun hideLoading(){
        progress.visibility = View.GONE
    }
}