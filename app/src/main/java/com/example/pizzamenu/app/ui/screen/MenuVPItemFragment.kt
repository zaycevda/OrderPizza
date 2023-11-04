package com.example.pizzamenu.app.ui.screen

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.isGone
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.pizzamenu.R
import com.example.pizzamenu.app.App
import com.example.pizzamenu.app.ui.adapter.ProductsAdapter
import com.example.pizzamenu.app.ui.util.ProductType
import com.example.pizzamenu.app.ui.util.showToast
import com.example.pizzamenu.app.viewmodel.FoodViewModel
import com.example.pizzamenu.app.viewmodel.viewModelFactory
import com.example.pizzamenu.databinding.FragmentMenuVpItemBinding
import kotlinx.coroutines.launch

class MenuVPItemFragment(
    private val productType: ProductType
) : Fragment(R.layout.fragment_menu_vp_item) {
    private var adapter: ProductsAdapter? = null
    private val binding by viewBinding(FragmentMenuVpItemBinding::bind)
    private val viewModel by viewModels<FoodViewModel>(
        factoryProducer = {
            viewModelFactory(
                FoodViewModel(
                    App.appModule.getDessertsUseCase,
                    App.appModule.getDrinksUseCase,
                    App.appModule.getPizzasUseCase,
                    App.appModule.getSushiUseCase,
                )
            )
        }

    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdapter()
        getProducts()
    }

    private fun initAdapter() {
        adapter = ProductsAdapter()

        binding.rvProducts.adapter = adapter
    }

    private fun getProducts() {
        viewModel.getProducts(productType = productType)
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.product.collect { screenState ->
                    screenState.on(
                        error = { throwable ->
                            showToast(message = throwable.message.toString())
                            binding.pbProducts.isGone = true
                            binding.rvProducts.isGone = false
                        },
                        loading = {
                            binding.pbProducts.isGone = false
                            binding.rvProducts.isGone = true
                        },
                        success = { products ->
                            adapter?.products = products
                            binding.pbProducts.isGone = true
                            binding.rvProducts.isGone = false
                        }
                    )
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        adapter = null
    }
}