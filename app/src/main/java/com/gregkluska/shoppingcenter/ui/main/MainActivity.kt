package com.gregkluska.shoppingcenter.ui.main

import android.os.Bundle
import android.util.Log
import com.gregkluska.shoppingcenter.R
import com.gregkluska.shoppingcenter.models.Store
import com.gregkluska.shoppingcenter.persistence.StoreDao
import com.gregkluska.shoppingcenter.ui.BaseActivity
import dagger.Provides
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    @Inject
    lateinit var storeDao: StoreDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d("StoreDao", "onCreate: ${storeDao.hashCode()}")
    }

}