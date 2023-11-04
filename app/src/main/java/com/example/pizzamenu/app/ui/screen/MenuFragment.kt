package com.example.pizzamenu.app.ui.screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.pizzamenu.R
import com.example.pizzamenu.app.ui.util.ProductType
import com.example.pizzamenu.app.ui.util.ProductType.DESSERTS
import com.example.pizzamenu.app.ui.util.ProductType.DRINKS
import com.example.pizzamenu.app.ui.util.ProductType.PIZZAS
import com.example.pizzamenu.app.ui.util.ProductType.SUSHI
import com.example.pizzamenu.databinding.FragmentMenuBinding
import com.google.android.material.tabs.TabLayoutMediator

class MenuFragment : Fragment(R.layout.fragment_menu) {
    private val binding by viewBinding(FragmentMenuBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initVPAdapter()
        initTabLayoutMediator()
    }

    private fun initVPAdapter() {
        binding.vpProducts.adapter = ProductsVPAdapter(
            childFragmentManager,
            lifecycle
        )
    }

    private fun initTabLayoutMediator() {
        val tabTitles = resources.getStringArray(R.array.tab_titles)
        TabLayoutMediator(binding.tabCategories, binding.vpProducts) { tab, position ->
            tab.text = tabTitles[position]
        }.attach()

        for (i in tabTitles.indices) {
            val textView = LayoutInflater.from(requireContext()).inflate(R.layout.tab_item, null) as TextView
            binding.tabCategories.getTabAt(i)?.customView = textView
        }
    }

    private class ProductsVPAdapter(
        fm: FragmentManager,
        lifecycle: Lifecycle
    ) : FragmentStateAdapter(fm, lifecycle) {
        override fun createFragment(position: Int) =
            when (ProductType.values()[position]) {
                PIZZAS -> MenuVPItemFragment(productType = PIZZAS)
                SUSHI -> MenuVPItemFragment(productType = SUSHI)
                DESSERTS -> MenuVPItemFragment(productType = DESSERTS)
                DRINKS -> MenuVPItemFragment(productType = DRINKS)
            }

        override fun getItemCount() = ProductType.values().size
    }
}