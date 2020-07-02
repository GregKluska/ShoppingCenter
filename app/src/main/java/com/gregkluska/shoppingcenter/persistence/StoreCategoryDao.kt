package com.gregkluska.shoppingcenter.persistence

import androidx.room.*
import com.gregkluska.shoppingcenter.models.CategoryWithStores
import com.gregkluska.shoppingcenter.models.StoreCategory

interface StoreCategoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertStoreCategories(vararg categories: StoreCategory): List<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertStoreCategory(category: StoreCategory): Long

    @Query("""
        UPDATE store_categories SET name = :name
        WHERE category_id = :id
    """)
    fun updateCategory(id: Int, name: String)

    @Delete
    fun deleteStore(category: StoreCategory)

    @Transaction
    @Query("SELECT * FROM store_categories")
    fun getCategoriesWithStores(): List<CategoryWithStores>

    @Transaction
    @Query("SELECT * FROM store_categories WHERE category_id = :id")
    fun getCategoryWithStores(id: Int): CategoryWithStores
}