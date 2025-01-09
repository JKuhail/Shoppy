package com.jkuhail.data.network

import com.jkuhail.data.BuildConfig


fun constructUrl(url: String): String {
    return when {
        url.contains(BuildConfig.BASE_URL) -> url
        url.startsWith("/") -> BuildConfig.BASE_URL + url.removePrefix("/")
        else -> BuildConfig.BASE_URL + url
    }
}