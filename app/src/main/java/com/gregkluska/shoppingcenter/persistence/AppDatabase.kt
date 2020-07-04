package com.gregkluska.shoppingcenter.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import com.gregkluska.shoppingcenter.models.Store
import com.gregkluska.shoppingcenter.models.StoreCategory
import com.gregkluska.shoppingcenter.models.StoreCategoryCrossRef

@Database(entities = [Store::class, StoreCategory::class, StoreCategoryCrossRef::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getStoreDao(): StoreDao

    abstract fun getStoreCategoryDao(): StoreCategoryDao

    companion object {

        const val DATABASE_NAME = "stores_db"
    }

}