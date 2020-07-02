package com.gregkluska.shoppingcenter.persistence

import androidx.room.*
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.OnConflictStrategy.REPLACE
import com.gregkluska.shoppingcenter.models.Store
import com.gregkluska.shoppingcenter.models.StoreWithCategories

@Dao
interface StoreDao {

    @Insert(onConflict = REPLACE)
    fun insertStores(vararg stores: Store): List<Long>

    @Insert(onConflict = REPLACE)
    fun insertStore(store: Store): Long

    @Query("""
        UPDATE stores SET name = :name, description = :description, logo = :logo, image = :image
        WHERE store_id = :id
    """)
    fun updateStore(id: Int, name: String, description: String, logo: String?, image: String?)

    @Delete
    fun deleteStore(store: Store)

    @Transaction
    @Query("SELECT * FROM stores")
    fun getStoresWithCategories(): List<StoreWithCategories>

    @Transaction
    @Query("SELECT * FROM stores WHERE store_id = :id")
    fun getStoreWithCategories(id: Int): StoreWithCategories
}