package com.gregkluska.shoppingcenter.models

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class CategoryWithStores (
    @Embedded
    val storeCategory: StoreCategory,
    @Relation(
        parentColumn = "category_id",
        entityColumn = "store_id",
        associateBy = Junction(StoreCategoryCrossRef::class)
    )
    val stores: List<Store>
)