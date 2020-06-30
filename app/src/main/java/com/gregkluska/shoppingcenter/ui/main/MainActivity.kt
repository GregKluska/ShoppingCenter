package com.gregkluska.shoppingcenter.ui.main

import android.os.Bundle
import com.gregkluska.shoppingcenter.R
import com.gregkluska.shoppingcenter.models.Store
import com.gregkluska.shoppingcenter.ui.BaseActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

}