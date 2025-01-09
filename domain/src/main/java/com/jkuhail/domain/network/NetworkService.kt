package com.jkuhail.domain.network

import com.jkuhail.domain.model.Product

interface NetworkService {
    suspend fun getProducts(): ResultWrapper<List<Product>, NetworkError>
}