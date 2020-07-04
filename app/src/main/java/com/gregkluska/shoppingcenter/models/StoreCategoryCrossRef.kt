package com.gregkluska.shoppingcenter.models

import androidx.room.*

@Entity(
    tableName = "store_category_ref",
    primaryKeys = ["store_id", "category_id"],
    indices = [
        Index(value = ["category_id"], unique = false)
    ]
)
data class StoreCategoryCrossRef(
    @ColumnInfo(name = "store_id")
    val storeId: Int,
    @ColumnInfo(name = "category_id")
    val categoryId: Int
){
    override fun toString(): String {
        return "StoreCategoryRef(storeId=$storeId, categoryId=$categoryId)"
    }
}