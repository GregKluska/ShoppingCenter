package com.gregkluska.shoppingcenter.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "store_category_ref",
    primaryKeys = ["store_id", "category_id"]
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