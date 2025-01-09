package com.jkuhail.data.repository

import com.jkuhail.domain.model.Product
import com.jkuhail.domain.network.NetworkError
import com.jkuhail.domain.network.NetworkService
import com.jkuhail.domain.network.ResultWrapper
import com.jkuhail.domain.repository.ProductRepository

class ProductRepositoryImp(
    private val networkService: NetworkService
) : ProductRepository {
    override suspend fun getProduct(): ResultWrapper<List<Product>, NetworkError> {
        return networkService.getProducts()
    }
}