package com.gregkluska.shoppingcenter.models

data class Store(
    val id: Int,
    val name: String,
    val description: String?,
    val logo: String?,
    val image: String?
) {

    override fun toString(): String {
        return "Store(id=$id, name='$name', description=$description, logo=$logo, image=$image)"
    }
}