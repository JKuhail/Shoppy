package com.jkuhail.data.network

import com.jkuhail.data.dto.ProductDto
import com.jkuhail.domain.model.Product
import com.jkuhail.domain.network.NetworkError
import com.jkuhail.domain.network.NetworkService
import com.jkuhail.domain.network.ResultWrapper
import com.jkuhail.domain.network.map
import io.ktor.client.HttpClient
import io.ktor.client.request.get

class NetworkServiceImp(
    private val httpClient: HttpClient
) : NetworkService {

    override suspend fun getProducts(): ResultWrapper<List<Product>, NetworkError> {
        return safeCall<List<ProductDto>> {
            httpClient.get(
                urlString = constructUrl("/products"),
            )
        }.map { response -> response.map { productDto -> productDto.toProduct() } }
    }
}