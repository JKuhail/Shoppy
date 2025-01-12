package com.jkuhail.shoppy.ui.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jkuhail.domain.network.onError
import com.jkuhail.domain.network.onSuccess
import com.jkuhail.domain.usecase.GetProductsUseCase
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getProductsUseCase: GetProductsUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(ProductsListState())
    val state = _state
        .onStart { loadProducts() }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = ProductsListState()
        )

    private val _events = Channel<ProductsListEvent>()
    val events = _events.receiveAsFlow()

    private fun loadProducts() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }

            getProductsUseCase
                .execute()
                .onSuccess { products ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            products = products
                        )
                    }
                }
                .onError { error ->
                    _state.update { it.copy(isLoading = false) }
                    _events.send(ProductsListEvent.Error(error))
                }
        }
    }
}