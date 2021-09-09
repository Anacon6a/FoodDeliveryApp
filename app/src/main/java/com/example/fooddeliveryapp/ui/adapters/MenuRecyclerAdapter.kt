package com.example.fooddeliveryapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fooddeliveryapp.data.model.MealModel
import com.example.fooddeliveryapp.data.remote.dto.MealRemote
import com.example.fooddeliveryapp.databinding.MealDataBinding


class MenuRecyclerAdapter(private val meals: List<MealModel>) :
    RecyclerView.Adapter<MenuRecyclerAdapter.MealViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        val binding = MealDataBinding.inflate(
            layoutInflater,
            parent,
            false
        )
        return MealViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MealViewHolder, position: Int) {
        val meal = meals[position]
        holder.bind(meal)
    }

    override fun getItemCount(): Int {
        return meals.size
    }

    inner class MealViewHolder(
        private val binding: MealDataBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(meal: MealModel) {
            binding.meal = meal
        }
    }
}