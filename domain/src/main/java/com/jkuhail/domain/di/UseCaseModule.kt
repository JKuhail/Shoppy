package com.jkuhail.domain.di

import com.jkuhail.domain.usecase.GetProductsUseCase
import org.koin.dsl.module

/**
 * The difference between 'factory' and 'single' is that 'single' will create a single instance
 * that can be used multiple times, whereas 'factory' will create a new instance each time it is
 * been called.
 */
val useCaseModule = module {
    factory { GetProductsUseCase(get()) }
}