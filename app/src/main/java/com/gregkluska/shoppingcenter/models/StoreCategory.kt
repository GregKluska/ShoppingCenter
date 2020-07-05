package com.gregkluska.shoppingcenter.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "store_categories")
data class StoreCategory (

    @SerializedName("id")
    @Expose
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "category_id")
    val id: Int,

    @SerializedName("name")
    @Expose
    @ColumnInfo(name = "name")
    val name: String
){
    override fun toString(): String {
        return "StoreCategory(id=$id, name='$name')"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as StoreCategory

        if (id != other.id) return false
        if (name != other.name) return false

        return true
    }

}