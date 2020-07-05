package com.gregkluska.shoppingcenter.api.responses

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

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
    override fun toString(): String {
        return "StoreCategoryResponse(statusCode=$statusCode, error='$error', message='$message', id=$id, name='$name')"
    }
}