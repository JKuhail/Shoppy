package com.jkuhail.domain.usecase

import com.jkuhail.domain.repository.ProductsRepository

class GetProductsUseCase(
    private val repository: ProductsRepository
) {
    suspend fun execute() = repository.getProducts()
}