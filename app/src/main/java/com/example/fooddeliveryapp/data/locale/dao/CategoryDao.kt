package com.example.fooddeliveryapp.data.locale.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.fooddeliveryapp.data.locale.entity.CategoryLocale

@Dao
interface CategoryDao{
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(categories: List<CategoryLocale>)

    @Query("SELECT * FROM category_table")
    suspend fun getCategories(): List<CategoryLocale>
}
