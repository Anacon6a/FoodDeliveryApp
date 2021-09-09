package com.example.fooddeliveryapp.extensions

import androidx.lifecycle.MutableLiveData

fun <T> MutableLiveData<ArrayList<T>>.addNewItemAt(index: Int, item: T) {
    val oldValue = this.value ?: arrayListOf()
    oldValue.add(index, item)
    this.value = oldValue
}

fun <T> MutableLiveData<ArrayList<T>>.addNewItem(item: T) {
    val oldValue = this.value ?: arrayListOf()
    oldValue.add(item)
    this.value = oldValue
}
