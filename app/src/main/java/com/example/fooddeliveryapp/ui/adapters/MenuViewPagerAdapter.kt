package com.example.fooddeliveryapp.ui.adapters

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fooddeliveryapp.data.remote.dto.MealRemote
import com.example.fooddeliveryapp.databinding.RecyclerDataBinding
import kotlin.math.log

class MenuViewPagerAdapter : RecyclerView.Adapter<MenuViewPagerAdapter.RecyclerViewHolder>() {

    var count = 0

    override fun getItemCount(): Int {
        return count
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = RecyclerDataBinding.inflate(
            layoutInflater,
            parent,
            false
        )
        return RecyclerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.bindRecycler(position)
    }

    inner class RecyclerViewHolder(
        private val binding: RecyclerDataBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bindRecycler(position: Int) {

            onCreateRecyclerViewHolder?.let {
                it(binding, position)
            }
        }
    }

    private var onCreateRecyclerViewHolder: ((RecyclerDataBinding, Int) -> Unit)? = null

    fun setOnCreateRecyclerViewHolder(listener: (RecyclerDataBinding, Int) -> Unit) {
        onCreateRecyclerViewHolder = listener
    }

}