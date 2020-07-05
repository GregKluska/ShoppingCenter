package com.gregkluska.shoppingcenter.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "stores")
data class Store(

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "store_id")
    val id: Int,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "description")
    val description: String?,

    @ColumnInfo(name = "logo")
    val logo: String?,

    @ColumnInfo(name = "image")
    val image: String?
) {

    override fun toString(): String {
        return "Store(id=$id, name='$name', description=$description, logo=$logo, image=$image)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Store

        if (id != other.id) return false
        if (name != other.name) return false
        if (description != other.description) return false
        if (logo != other.logo) return false
        if (image != other.image) return false

        return true
    }

}