package com.jkuhail.shoppy.ui.feature.home

import androidx.compose.runtime.Immutable
import com.jkuhail.domain.model.Product

@Immutable
data class ProductsListState(
    val isLoading: Boolean = false,
    val products: List<Product> = emptyList(),
    var selectedProduct: Product? = null
)
