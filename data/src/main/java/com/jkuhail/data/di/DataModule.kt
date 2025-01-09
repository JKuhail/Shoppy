package com.jkuhail.data.di

import org.koin.dsl.module

val dataModule = module {
    includes(networkModule, repositoryModule) // these modules will be created when the app runs.
}