package com.example.fooddeliveryapp.data.repository

import android.util.Log
import com.example.fooddeliveryapp.data.locale.dao.CategoryDao
import com.example.fooddeliveryapp.data.locale.dao.MealDao
import com.example.fooddeliveryapp.data.mapper.*
import com.example.fooddeliveryapp.data.model.MealModel
import com.example.fooddeliveryapp.data.remote.api.RestApi
import com.example.fooddeliveryapp.data.remote.dto.MealRemote
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class MealRepository(
    private val restApi: RestApi,
    private val mealDao: MealDao
) {

    suspend fun getMealsByCategory(categoryName: String, categoryId: String) = flow {
        emit(mapMealLM(mealDao.getMealsByCategory(categoryId)))
        val result = restApi.getMealsByCategory(categoryName)

        result.body()?.meals?.let { meals ->
            if (result.isSuccessful) {
                mealDao.insert(mapMealRL(meals, categoryId))
                emit(mapMealRM(meals))
            }
        }
    }.flowOn(Dispatchers.IO).catch {
        Log.e("MealRepository", "$it")
    }

}