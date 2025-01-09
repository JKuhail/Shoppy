package com.jkuhail.data.repository

import com.jkuhail.domain.model.Product
import com.jkuhail.domain.network.NetworkError
import com.jkuhail.domain.network.NetworkService
import com.jkuhail.domain.network.ResultWrapper
import com.jkuhail.domain.repository.ProductsRepository

class ProductsRepositoryImp(
    private val networkService: NetworkService
) : ProductsRepository {
    override suspend fun getProducts(): ResultWrapper<List<Product>, NetworkError> {
        return networkService.getProducts()
    }
}