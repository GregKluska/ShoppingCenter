package com.gregkluska.shoppingcenter.api.responses

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.gregkluska.shoppingcenter.models.StoreCategory

class StoreCategoryResponse(

    @SerializedName("statusCode")
    @Expose
    val statusCode: Int,

    @SerializedName("error")
    @Expose
    val error: String,

    @SerializedName("message")
    @Expose
    val message: String,

    @SerializedName("id")
    @Expose
    val id: Int,

    @SerializedName("name")
    @Expose
    val name: String
) {

    fun toStoreCategory(): StoreCategory {
        return StoreCategory(
            id = id,
            name = name
        )
    }

    override fun toString(): String {
        return "StoreCategoryResponse(statusCode=$statusCode, error='$error', message='$message', id=$id, name='$name')"
    }
}