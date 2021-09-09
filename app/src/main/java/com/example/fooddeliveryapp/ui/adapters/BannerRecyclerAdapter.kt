package com.example.fooddeliveryapp.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fooddeliveryapp.databinding.BannerDataBinding

class BannerRecyclerAdapter(private val banners: List<Int>) :
    RecyclerView.Adapter<BannerRecyclerAdapter.BannerViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BannerRecyclerAdapter.BannerViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        val binding = BannerDataBinding.inflate(
            layoutInflater,
            parent,
            false
        )
        return BannerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BannerViewHolder, position: Int) {
        val banner = banners[position]
        holder.bind(banner)
    }

    override fun getItemCount(): Int {
        return banners.size
    }

    inner class BannerViewHolder(
        private val binding: BannerDataBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("UseCompatLoadingForDrawables")
        fun bind(banner: Int) {
            binding.bannerImage.setImageDrawable(binding.root.context.getDrawable(banner))
        }
    }
}