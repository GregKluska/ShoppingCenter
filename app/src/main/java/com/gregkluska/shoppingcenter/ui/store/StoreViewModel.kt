package com.gregkluska.shoppingcenter.ui.store

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.gregkluska.shoppingcenter.api.responses.StoreCategoryResponse
import com.gregkluska.shoppingcenter.api.responses.StoreResponse
import com.gregkluska.shoppingcenter.repository.main.MainRepository
import com.gregkluska.shoppingcenter.util.GenericApiResponse

class StoreViewModel
@ViewModelInject
constructor(
    private val mainRepository: MainRepository
): ViewModel() {

    fun testStoreListRequest(): LiveData<GenericApiResponse<List<StoreResponse>>> {
        return mainRepository.testStoreListRequest();
    }

    fun testStoreRequest(): LiveData<GenericApiResponse<StoreResponse>> {
        return mainRepository.testStoreRequest();
    }

    fun testStoreCatListRequest(): LiveData<GenericApiResponse<List<StoreCategoryResponse>>> {
        return mainRepository.testStoreCatListRequest();
    }

    fun testStoreCatRequest(): LiveData<GenericApiResponse<StoreCategoryResponse>> {
        return mainRepository.testStoreCatRequest();
    }

}