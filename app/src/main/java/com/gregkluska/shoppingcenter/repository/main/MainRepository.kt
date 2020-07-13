package com.gregkluska.shoppingcenter.repository.main

import androidx.lifecycle.LiveData
import com.gregkluska.shoppingcenter.api.ApiService
import com.gregkluska.shoppingcenter.api.responses.StoreCategoryResponse
import com.gregkluska.shoppingcenter.api.responses.StoreResponse
import com.gregkluska.shoppingcenter.persistence.StoreCategoryDao
import com.gregkluska.shoppingcenter.persistence.StoreDao
import com.gregkluska.shoppingcenter.util.Constants.Companion.STORES_PER_PAGE
import com.gregkluska.shoppingcenter.util.GenericApiResponse
import javax.inject.Inject

private const val TAG = "MainRepository"

class MainRepository
@Inject
constructor(
    val storeDao: StoreDao,
    val storeCategoryDao: StoreCategoryDao,
    val apiService: ApiService
)
{


    fun testStoreListRequest(): LiveData<GenericApiResponse<List<StoreResponse>>> {
        return apiService.getStoreList(1, STORES_PER_PAGE)
    }

    fun testStoreCatListRequest(): LiveData<GenericApiResponse<List<StoreCategoryResponse>>> {
        return apiService.getStoreCategoryList()
    }

    fun testStoreRequest(): LiveData<GenericApiResponse<StoreResponse>> {
        return apiService.getStore(1)
    }

    fun testStoreCatRequest(): LiveData<GenericApiResponse<StoreCategoryResponse>> {
        return apiService.getStoreCategory(1)
    }
}