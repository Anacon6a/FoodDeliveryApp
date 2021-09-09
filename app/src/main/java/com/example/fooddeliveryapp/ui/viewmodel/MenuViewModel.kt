package com.example.fooddeliveryapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fooddeliveryapp.R
import com.example.fooddeliveryapp.data.model.CategoryModel
import com.example.fooddeliveryapp.data.repository.CategoryRepository
import com.example.fooddeliveryapp.data.repository.MealRepository
import com.example.fooddeliveryapp.ui.adapters.BannerRecyclerAdapter
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MenuViewModel(
    private val mealRepository: MealRepository,
    private val categoryRepository: CategoryRepository
) : ViewModel() {
    private val banners = listOf(R.drawable.banner1, R.drawable.banner2, )
    val bannerRecyclerAdapter = BannerRecyclerAdapter(banners)

    val cities = listOf("Москва", "Санкт-Петербург", "Екатеринбург", "Нижний Новгород")

    private val _categories = MutableLiveData<List<CategoryModel>>()
    val categories: LiveData<List<CategoryModel>>
        get() = _categories

    private val selectCity = MutableLiveData<Int>()

    init {
        getCategories()
    }

    private fun getCategories() {
        viewModelScope.launch {
            categoryRepository.getCategories().collect {
                _categories.postValue(it)
            }
        }
    }

    fun getMeals(position: Int) = flow {
        categories.value?.let { categories ->
            if (categories.size > position) {
                mealRepository.getMealsByCategory(
                    categories[position].name,
                    categories[position].id
                )
                    .collect {
                        emit(it)
                    }
            }
        }
    }

    fun selectCity(position: Int) {
        viewModelScope.launch {
            selectCity.postValue(position)
        }
    }
}

