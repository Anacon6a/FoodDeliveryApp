package com.example.fooddeliveryapp

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import androidx.room.Dao
import com.example.fooddeliveryapp.data.locale.FoodDeliveryDataBase
import com.example.fooddeliveryapp.data.locale.dao.CategoryDao
import com.example.fooddeliveryapp.data.remote.api.RestApi
import com.example.fooddeliveryapp.data.remote.api.ServiceBuilder
import com.example.fooddeliveryapp.data.repository.CategoryRepository
import com.example.fooddeliveryapp.data.repository.MealRepository
import com.example.fooddeliveryapp.extensions.bindViewModel
import com.example.fooddeliveryapp.ui.viewmodel.MenuViewModel
import com.example.fooddeliveryapp.ui.viewmodel.ViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.direct
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton
import kotlin.math.sin

class FoodDeliveryApp : Application(), KodeinAware {

    override val kodein = Kodein.lazy {
        import(androidXModule(this@FoodDeliveryApp))

        val database by lazy { FoodDeliveryDataBase.getDatabase(this@FoodDeliveryApp) }

        bind<ViewModelProvider.Factory>() with singleton { ViewModelFactory(kodein.direct) }

        bindViewModel<MenuViewModel>() with provider {
            MenuViewModel(instance(), instance())
        }

        bind() from singleton { CategoryRepository(instance(), database.categoryDao()) }
        bind() from singleton { MealRepository(instance(), database.mealDao()) }

        bind() from singleton { RestApi(ServiceBuilder.restApiService) }
    }

}