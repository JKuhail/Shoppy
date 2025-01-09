package com.jkuhail.data.network

import com.jkuhail.domain.network.NetworkError
import com.jkuhail.domain.network.ResultWrapper
import io.ktor.client.statement.HttpResponse
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.coroutines.ensureActive
import kotlinx.serialization.SerializationException
import kotlin.coroutines.coroutineContext

suspend inline fun <reified T> safeCall(
    execute: () -> HttpResponse
): ResultWrapper<T, NetworkError> {
    val response = try {
        execute()
    } catch (e: UnresolvedAddressException) {
        return ResultWrapper.Error(NetworkError.NO_INTERNET_CONNECTION)
    } catch (e: SerializationException) {
        return ResultWrapper.Error(NetworkError.SERIALIZATION_ERROR)
    } catch (e: Exception) {
        //make sure that if the coroutine throw a cancellation exception,
        // it is not handled by this generic exception handler.
        // So the main thread will be notified of the cancellation.
        coroutineContext.ensureActive()
        return ResultWrapper.Error(NetworkError.UNKNOWN_ERROR)
    }

    return responseToResult(response)
}