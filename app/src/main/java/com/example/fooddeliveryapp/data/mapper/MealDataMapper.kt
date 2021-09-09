package com.example.fooddeliveryapp.data.mapper

import com.example.fooddeliveryapp.data.locale.entity.CategoryLocale
import com.example.fooddeliveryapp.data.locale.entity.MealLocale
import com.example.fooddeliveryapp.data.model.CategoryModel
import com.example.fooddeliveryapp.data.model.MealModel
import com.example.fooddeliveryapp.data.remote.dto.CategoryRemote
import com.example.fooddeliveryapp.data.remote.dto.MealRemote

inline fun mapMealRM(meals: List<MealRemote>) = meals.map {
    MealModel(it.id, it.name, it.image)
}

inline fun mapMealLM(meals: List<MealLocale>)= meals.map {
    MealModel(it.id, it.name, it.image)
}

inline fun mapMealRL(meals: List<MealRemote>, categoryId: String)= meals.map {
    MealLocale(it.id, it.name, it.image, categoryId)
}

