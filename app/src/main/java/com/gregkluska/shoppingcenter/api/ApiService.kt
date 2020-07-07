package com.gregkluska.shoppingcenter.api

import androidx.lifecycle.LiveData
import com.gregkluska.shoppingcenter.api.responses.StoreCategoryResponse
import com.gregkluska.shoppingcenter.api.responses.StoreResponse
import com.gregkluska.shoppingcenter.util.GenericApiResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("stores/{id}")
    fun getStore(
        @Path("id")
        id:Int
    ): LiveData<GenericApiResponse<StoreResponse>>

    @GET("stores")
    fun getStoreList(
        @Query("_start")
        start: Int,
        @Query("_limit")
        limit: Int
    ): LiveData<GenericApiResponse<List<StoreResponse>>>

    @GET("store-categories/{id}")
    fun getStoreCategory(
        @Path("id")
        id: Int
    ): LiveData<GenericApiResponse<StoreCategoryResponse>>

    @GET("store-categories")
    fun getStoreCategoryList(): LiveData<GenericApiResponse<List<StoreCategoryResponse>>>
}