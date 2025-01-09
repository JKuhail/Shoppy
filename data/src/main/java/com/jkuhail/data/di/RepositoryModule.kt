package com.jkuhail.data.di

import com.jkuhail.data.repository.ProductRepositoryImp
import com.jkuhail.domain.repository.ProductRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val repositoryModule = module {
    singleOf(::ProductRepositoryImp).bind<ProductRepository>()
}