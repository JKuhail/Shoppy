package com.jkuhail.shoppy

import android.app.Application
import com.jkuhail.data.di.dataModule
import com.jkuhail.domain.di.domainModule
import com.jkuhail.shoppy.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class ShoppyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@ShoppyApplication)
            modules(
                listOf(
                    presentationModule,
                    domainModule,
                    dataModule
                )
            )
        }

    }

}