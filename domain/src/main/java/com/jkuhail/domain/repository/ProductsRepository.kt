package com.jkuhail.domain.repository

import com.jkuhail.domain.model.Product
import com.jkuhail.domain.network.NetworkError
import com.jkuhail.domain.network.ResultWrapper

interface ProductsRepository {
    suspend fun getProducts(): ResultWrapper<List<Product>, NetworkError>
}