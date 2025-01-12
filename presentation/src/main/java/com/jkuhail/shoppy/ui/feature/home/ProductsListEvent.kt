package com.jkuhail.shoppy.ui.feature.home

import com.jkuhail.domain.network.NetworkError


sealed interface ProductsListEvent {
    data class Error(val error: NetworkError) : ProductsListEvent
}