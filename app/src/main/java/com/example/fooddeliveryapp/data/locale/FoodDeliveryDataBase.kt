package com.example.fooddeliveryapp.data.locale

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.fooddeliveryapp.data.locale.dao.CategoryDao
import com.example.fooddeliveryapp.data.locale.dao.MealDao
import com.example.fooddeliveryapp.data.locale.entity.CategoryLocale
import com.example.fooddeliveryapp.data.locale.entity.MealLocale

@Database(entities = arrayOf(CategoryLocale::class, MealLocale::class), version = 1, exportSchema = false )
abstract class FoodDeliveryDataBase: RoomDatabase() {

    abstract fun categoryDao(): CategoryDao
    abstract fun mealDao(): MealDao

    companion object {
        @Volatile
        private var INSTANCE: FoodDeliveryDataBase? = null

        fun getDatabase(context: Context): FoodDeliveryDataBase {

            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FoodDeliveryDataBase::class.java,
                    "food_delivery_database"
                ).build()
                INSTANCE = instance
                // return
                instance
            }
        }
    }
}