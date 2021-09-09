package com.example.fooddeliveryapp.data.mapper

import com.example.fooddeliveryapp.data.locale.entity.CategoryLocale
import com.example.fooddeliveryapp.data.locale.entity.MealLocale
import com.example.fooddeliveryapp.data.model.CategoryModel
import com.example.fooddeliveryapp.data.model.MealModel
import com.example.fooddeliveryapp.data.remote.dto.CategoryRemote
import com.example.fooddeliveryapp.data.remote.dto.MealRemote

inline fun mapCategoryRM(categories: List<CategoryRemote>) = categories.map {
    CategoryModel(it.id, it.name)
}


inline fun mapCategoryLM(categories: List<CategoryLocale>) = categories.map {
    CategoryModel(it.id, it.name)
}


inline fun mapCategoryRL(categories: List<CategoryRemote>)  = categories.map {
    CategoryLocale(it.id, it.name)
}

