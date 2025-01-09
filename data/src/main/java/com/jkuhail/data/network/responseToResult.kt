package com.jkuhail.data.network

import com.jkuhail.domain.network.NetworkError
import com.jkuhail.domain.network.ResultWrapper
import io.ktor.client.call.NoTransformationFoundException
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse

/**
 * The 'suspend' keyword is used to indicate that the function is a coroutine function, which means
 * that it should be handled in a coroutine context.
 *
 * The 'inline' keyword is used to instruct the compiler to replace calls to a function with the
 * function's body at the call site. We used it here since the 'reified' keyword can not be used
 * unless the function is inlined.
 *
 * The 'reified' keyword is used to indicate that the type parameter T is a real type, not a type
 * of Object. This allows you to perform operations on the type parameter that are not normally
 * possible with generic types due to type erasure.
 *
 * */
suspend inline fun <reified T> responseToResult(
    response: HttpResponse
): ResultWrapper<T, NetworkError> {
    return when (response.status.value) {
        in 200..299 -> {
            try {
                ResultWrapper.Success(response.body<T>())
            } catch (e: NoTransformationFoundException) {
                ResultWrapper.Error(NetworkError.SERIALIZATION_ERROR)
            }
        }

        408 -> ResultWrapper.Error(NetworkError.REQUEST_TIMEOUT)
        429 -> ResultWrapper.Error(NetworkError.TOO_MANY_REQUESTS)
        in 500..599 -> ResultWrapper.Error(NetworkError.SERVER_ERROR)
        else -> ResultWrapper.Error(NetworkError.UNKNOWN_ERROR)
    }
}