package com.jkuhail.data.di

import com.jkuhail.data.repository.ProductsRepositoryImp
import com.jkuhail.domain.repository.ProductsRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val repositoryModule = module {
    singleOf(::ProductsRepositoryImp).bind<ProductsRepository>()
}