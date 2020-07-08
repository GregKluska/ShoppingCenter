package com.gregkluska.shoppingcenter.ui.main

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.gregkluska.shoppingcenter.R
import com.gregkluska.shoppingcenter.api.responses.StoreResponse
import com.gregkluska.shoppingcenter.models.Store
import com.gregkluska.shoppingcenter.models.StoreCategory
import com.gregkluska.shoppingcenter.ui.BaseActivity
import com.gregkluska.shoppingcenter.util.GenericApiResponse.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

private const val TAG = "MainActivity"

@AndroidEntryPoint
class MainActivity : BaseActivity(), MainRecyclerAdapter.Interaction {

    private val viewModel: MainViewModel by viewModels()

    lateinit var mainRecyclerAdapter: MainRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRecyclerView()
        subscribeObservers()
    }

    private fun initRecyclerView() {
        recycler_view.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            mainRecyclerAdapter = MainRecyclerAdapter(this@MainActivity)
            adapter = mainRecyclerAdapter
        }
    }

    private fun subscribeObservers() {
        viewModel.testStoreListRequest().observe(this, Observer { response ->
            when (response) {

                is ApiSuccessResponse -> {
                    Log.d(TAG, "STORELIST RESPONSE : ${response.body}")



                    response.body?.let{ list: List<StoreResponse> ->

                        val newList = list.map{
                            it.toStore()
                        }
                        mainRecyclerAdapter.submitStoreList(newList)
                        Log.d(TAG, "subscribeObservers: $newList")
                    }
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

    override fun onCategoryItemSelected(position: Int, item: StoreCategory) {
        TODO("Not yet implemented")
    }

    override fun onStoreItemSelected(position: Int, item: Store) {
        TODO("Not yet implemented")
    }


}