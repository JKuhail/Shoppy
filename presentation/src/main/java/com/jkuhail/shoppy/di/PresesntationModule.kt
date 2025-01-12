package com.jkuhail.shoppy.di

import org.koin.dsl.module

val presentationModule = module {
    includes(viewModelModule)
}