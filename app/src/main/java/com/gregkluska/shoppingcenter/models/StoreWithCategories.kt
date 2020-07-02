package com.gregkluska.shoppingcenter.models

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class StoreWithCategories(
    @Embedded
    val store: Store,
    @Relation(
        parentColumn = "store_id",
        entityColumn = "category_id",
        associateBy = Junction(StoreCategoryCrossRef::class)
    )
    val categories: List<StoreCategory>
)