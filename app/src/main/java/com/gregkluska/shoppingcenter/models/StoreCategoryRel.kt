package com.gregkluska.shoppingcenter.models

data class StoreCategoryRel(
    val id: Int,
    val storeId: Int,
    val categoryId: Int
){
    override fun toString(): String {
        return "StoreCategoryRel(id=$id, storeId=$storeId, categoryId=$categoryId)"
    }
}