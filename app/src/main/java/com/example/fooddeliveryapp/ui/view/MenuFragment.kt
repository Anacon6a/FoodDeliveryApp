package com.example.fooddeliveryapp.ui.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fooddeliveryapp.R
import com.example.fooddeliveryapp.data.model.CategoryModel
import com.example.fooddeliveryapp.databinding.FragmentMenuBinding
import com.example.fooddeliveryapp.extensions.kodeinViewModel
import com.example.fooddeliveryapp.ui.adapters.MenuRecyclerAdapter
import com.example.fooddeliveryapp.ui.adapters.MenuViewPagerAdapter
import com.example.fooddeliveryapp.ui.viewmodel.MenuViewModel
import com.google.android.material.appbar.AppBarLayout.OnOffsetChangedListener
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein


class MenuFragment : Fragment(), KodeinAware {

    override val kodein by kodein()
    private lateinit var binding: FragmentMenuBinding
    private val viewModel: MenuViewModel by kodeinViewModel()
    private var viewPagerAdapter: MenuViewPagerAdapter = MenuViewPagerAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentMenuBinding.inflate(inflater, container, false)
        binding.menuViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        setBannerRecyclerAdapter()
        subscriptions()
        setSpinner()

        return binding.root
    }

    private fun setBannerRecyclerAdapter() {
        lifecycleScope.launch {
            binding.bannerRecyclerView.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                adapter = viewModel.bannerRecyclerAdapter
            }
        }
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun subscriptions() {
        viewModel.categories.observe(viewLifecycleOwner, {
            setViewPagerAdapter(it)
            setTabLayout(it)
        })
        viewPagerAdapter.setOnCreateRecyclerViewHolder { recyclerBinding, position ->
            lifecycleScope.launch {
                viewModel.getMeals(position).collect { meals ->
                    recyclerBinding.menuRecycler.apply {
                        layoutManager =
                            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                        adapter = MenuRecyclerAdapter(meals)
                    }
                }
            }
        }
        binding.appBarLayout.addOnOffsetChangedListener(OnOffsetChangedListener { appBarLayout, offset ->
            binding.menuTabLayout.background = context?.resources?.getDrawable(R.color.alabaster)
            if (offset < -200){
                binding.menuTabLayout.background = context?.resources?.getDrawable(R.color.white)
            } else{
                binding.menuTabLayout.background = context?.resources?.getDrawable(R.color.alabaster)
            }
        })
    }


    private fun setViewPagerAdapter(categories: List<CategoryModel>) {
        binding.menuView.adapter = viewPagerAdapter
        viewPagerAdapter.count = categories.size
        viewPagerAdapter.notifyDataSetChanged()
    }

    private fun setTabLayout(categories: List<CategoryModel>) {
        lifecycleScope.launch {
            TabLayoutMediator(
                binding.menuTabLayout,
                binding.menuView
            ) { tabLayout, position ->
                tabLayout.text = categories[position].name
            }.attach()
        }
    }

    private fun setSpinner() {
        lifecycleScope.launch {
            binding.citiesSpinner.adapter = ArrayAdapter(
                requireContext(),
                R.layout.list_item,
                viewModel.cities
            )

            binding.citiesSpinner.onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {
                    override fun onNothingSelected(parent: AdapterView<*>?) {
                        TODO("Not yet implemented")
                    }

                    override fun onItemSelected(
                        parent: AdapterView<*>?,
                        view: View?,
                        position: Int,
                        id: Long
                    ) {
                        viewModel.selectCity(position)
                    }
                }
        }
    }

}