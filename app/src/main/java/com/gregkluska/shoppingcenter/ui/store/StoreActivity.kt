package com.gregkluska.shoppingcenter.ui.store

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.gregkluska.shoppingcenter.R
import com.gregkluska.shoppingcenter.ui.BaseActivity
import com.gregkluska.shoppingcenter.ui.main.MainViewModel
import com.gregkluska.shoppingcenter.util.GenericApiResponse
import dagger.hilt.android.AndroidEntryPoint

private const val TAG = "StoreActivity"

@AndroidEntryPoint
class StoreActivity : BaseActivity() {

    private val viewModel: StoreViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.testStoreListRequest().observe(this, Observer { response ->
            when (response) {

                is GenericApiResponse.ApiSuccessResponse -> {
                    Log.d(TAG, "STORELIST RESPONSE : ${response.body}")
                }

                is GenericApiResponse.ApiErrorResponse -> {
                    Log.d(TAG, "STORELIST RESPONSE : ${response.errorMessage}")
                }

                is GenericApiResponse.ApiEmptyResponse -> {
                    Log.d(TAG, "STORELIST RESPONSE : Empty Response")
                }

            }
        })
    }
}