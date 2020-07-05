package com.gregkluska.shoppingcenter.api.responses

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.gregkluska.shoppingcenter.models.Store

class StoreResponse (

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
    val name: String,

    @SerializedName("description")
    @Expose
    val description: String,

    @SerializedName("logo")
    @Expose
    val logo: String,

    @SerializedName("assets")
    @Expose
    val image: String,

    @SerializedName("store_categories")
    @Expose
    val catID: List<Int>
) {

    fun toStore(): Store {
        return Store(
            id = id,
            name = name,
            description = description,
            logo = logo,
            image = image
        )
    }

    override fun toString(): String {
        return "StoreResponse(statusCode=$statusCode, error='$error', message='$message', id=$id, name='$name', description='$description', logo='$logo', image='$image', catID=$catID)"
    }
}