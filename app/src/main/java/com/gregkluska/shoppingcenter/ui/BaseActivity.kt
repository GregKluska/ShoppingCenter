package com.gregkluska.shoppingcenter.ui

import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.gregkluska.shoppingcenter.R
import kotlinx.android.synthetic.main.activity_base.*

abstract class BaseActivity : AppCompatActivity() {

    var mProgressBar : ProgressBar? = null

    override fun setContentView(layoutResID: Int) {
        var constraintLayout: ConstraintLayout = layoutInflater.inflate(R.layout.activity_base, null) as ConstraintLayout
        var frameLayout: FrameLayout = constraintLayout.findViewById(R.id.activity_content)
        mProgressBar = constraintLayout.findViewById(R.id.progress_bar) as ProgressBar

        layoutInflater.inflate(layoutResID, frameLayout, true)
        super.setContentView(constraintLayout)
    }

    public fun showProgressBar(visibility: Boolean) {
        mProgressBar?.let {
            it.visibility = if (visibility) View.VISIBLE else View.GONE
        }
    }
}