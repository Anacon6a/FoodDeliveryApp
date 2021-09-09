package com.example.fooddeliveryapp.data.locale.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.fooddeliveryapp.data.locale.entity.MealLocale
import kotlinx.coroutines.flow.Flow

@Dao
interface MealDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(meals: List<MealLocale>)

    @Query("SELECT * FROM meal_table WHERE categoryId = :categoryId")
    suspend fun getMealsByCategory(categoryId: String): List<MealLocale>
}