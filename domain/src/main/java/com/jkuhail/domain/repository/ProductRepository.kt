package com.jkuhail.domain.repository

import com.jkuhail.domain.model.Product
import com.jkuhail.domain.network.NetworkError
import com.jkuhail.domain.network.ResultWrapper

interface ProductRepository {
    suspend fun getProduct(): ResultWrapper<List<Product>, NetworkError>
}