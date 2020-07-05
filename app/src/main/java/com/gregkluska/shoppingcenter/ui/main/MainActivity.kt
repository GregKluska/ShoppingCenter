package com.gregkluska.shoppingcenter.ui.main

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.gregkluska.shoppingcenter.R
import com.gregkluska.shoppingcenter.ui.BaseActivity
import com.gregkluska.shoppingcenter.util.GenericApiResponse
import com.gregkluska.shoppingcenter.util.GenericApiResponse.*
import dagger.hilt.android.AndroidEntryPoint

private const val TAG = "MainActivity"

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.testStoreListRequest().observe(this, Observer { response ->
            when (response) {
                
                is ApiSuccessResponse -> {
                    Log.d(TAG, "STORELIST RESPONSE : ${response.body}")
                }

                is ApiErrorResponse -> {
                    Log.d(TAG, "STORELIST RESPONSE : ${response.errorMessage}")
                }

                is ApiEmptyResponse -> {
                    Log.d(TAG, "STORELIST RESPONSE : Empty Response")
                }
                
            }
        })
    }



}