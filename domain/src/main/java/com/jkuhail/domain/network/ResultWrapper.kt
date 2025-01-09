package com.jkuhail.domain.network

// Creates an alias for the Error type, named DomainError.
typealias DomainError = Error

/**
 * A sealed interface is a type of interface that restricts the possible implementations
 * of the interface to a known set of subclasses defined within the same module and package.
 *
 */
sealed interface ResultWrapper<out D, out E: Error> {
    data class Success<out D>(val data: D): ResultWrapper<D, Nothing>
    data class Error<out E: DomainError>(val error: E): ResultWrapper<Nothing, E>
}

inline fun <T, E: Error, R> ResultWrapper<T, E>.map(map: (T) -> R): ResultWrapper<R, E> {
    return when(this) {
        is ResultWrapper.Error -> ResultWrapper.Error(error)
        is ResultWrapper.Success -> ResultWrapper.Success(map(data))
    }
}

fun <T, E: Error> ResultWrapper<T, E>.asEmptyDataResult(): EmptyResult<E> {
    return map {  }
}

inline fun <T, E: Error> ResultWrapper<T, E>.onSuccess(action: (T) -> Unit): ResultWrapper<T, E> {
    return when(this) {
        is ResultWrapper.Error -> this
        is ResultWrapper.Success -> {
            action(data)
            this
        }
    }
}
inline fun <T, E: Error> ResultWrapper<T, E>.onError(action: (E) -> Unit): ResultWrapper<T, E> {
    return when(this) {
        is ResultWrapper.Error -> {
            action(error)
            this
        }
        is ResultWrapper.Success -> this
    }
}

// Creates an alias for Result<Unit, E>, representing a result with no data.
typealias EmptyResult<E> = ResultWrapper<Unit, E>