package com.example.fooddeliveryapp.data.locale.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "meal_table",
    foreignKeys = arrayOf(
        ForeignKey(
            entity = CategoryLocale::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("categoryId")
        )
    )
)
data class MealLocale(
    @PrimaryKey val id: String,
    val name: String,
    val image: String,
    val categoryId: String
)
