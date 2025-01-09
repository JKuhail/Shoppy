package com.jkuhail.data.di

import com.jkuhail.data.network.HttpClientFactory
import com.jkuhail.data.network.NetworkServiceImp
import com.jkuhail.domain.network.NetworkService
import io.ktor.client.engine.cio.CIO
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val networkModule = module {
    single { HttpClientFactory.create(CIO.create()) }

    singleOf(::NetworkServiceImp).bind<NetworkService>()

}