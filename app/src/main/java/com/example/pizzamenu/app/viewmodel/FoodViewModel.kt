package com.example.pizzamenu.app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pizzamenu.app.ui.util.ProductType
import com.example.pizzamenu.app.viewmodel.ScreenState.ErrorScreenState
import com.example.pizzamenu.app.viewmodel.ScreenState.LoadingScreenState
import com.example.pizzamenu.app.viewmodel.ScreenState.SuccessScreenState
import com.example.pizzamenu.domain.model.Product
import com.example.pizzamenu.domain.usecase.GetDessertsUseCase
import com.example.pizzamenu.domain.usecase.GetDrinksUseCase
import com.example.pizzamenu.domain.usecase.GetPizzasUseCase
import com.example.pizzamenu.domain.usecase.GetSushiUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FoodViewModel(
    private val getDessertsUseCase: GetDessertsUseCase,
    private val getDrinksUseCase: GetDrinksUseCase,
    private val getPizzasUseCase: GetPizzasUseCase,
    private val getSushiUseCase: GetSushiUseCase
) : ViewModel() {
    private val _product = MutableStateFlow<ScreenState<List<Product>>>(value = LoadingScreenState())
    val product = _product.asStateFlow()

    fun getProducts(productType: ProductType) {
        val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
            _product.value = ErrorScreenState(throwable = throwable)
        }

        viewModelScope.launch(coroutineExceptionHandler) {
            when (productType) {
                ProductType.PIZZAS -> {
                    val pizzas = getPizzasUseCase.execute()
                    _product.value = SuccessScreenState(value = pizzas)
                }

                ProductType.SUSHI -> {
                    val sushi = getSushiUseCase.execute()
                    _product.value = SuccessScreenState(value = sushi)
                }

                ProductType.DESSERTS -> {
                    val desserts = getDessertsUseCase.execute()
                    _product.value = SuccessScreenState(value = desserts)
                }

                ProductType.DRINKS -> {
                    val drinks = getDrinksUseCase.execute()
                    _product.value = SuccessScreenState(value = drinks)
                }
            }
        }
    }
}