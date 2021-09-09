package com.example.fooddeliveryapp.data.locale.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "category_table")
data class CategoryLocale(
    @PrimaryKey val id: String,
    val name: String,
)


