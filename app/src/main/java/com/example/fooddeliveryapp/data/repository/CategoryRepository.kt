package com.example.fooddeliveryapp.data.repository


import android.util.Log
import com.example.fooddeliveryapp.data.locale.dao.CategoryDao
import com.example.fooddeliveryapp.data.locale.entity.CategoryLocale
import com.example.fooddeliveryapp.data.mapper.*
import com.example.fooddeliveryapp.data.model.CategoryModel
import com.example.fooddeliveryapp.data.remote.api.RestApi
import com.example.fooddeliveryapp.data.remote.dto.CategoryRemote
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class CategoryRepository(
    private val restApi: RestApi,
    private val categoryDao: CategoryDao
) {

    suspend fun getCategories() = flow {
        emit(mapCategoryLM(categoryDao.getCategories()))
        Log.i("CategoryRepository","да")
        val result = restApi.getCategories()

        result.body()?.categories?.let { categories ->
            if (result.isSuccessful) {
                categoryDao.insert(mapCategoryRL(categories))
                emit(mapCategoryRM(categories))

            }
        }
    }.flowOn(Dispatchers.IO).catch {
        Log.e("CategoryRepository", "$it")
    }

}