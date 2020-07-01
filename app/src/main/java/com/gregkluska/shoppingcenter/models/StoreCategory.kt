package com.gregkluska.shoppingcenter.models

data class StoreCategory (
    val id: Int,
    val name: String
){
    override fun toString(): String {
        return "StoreCategory(id=$id, name='$name')"
    }
}