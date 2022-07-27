package br.com.coupledev.organizze.core

sealed class Resource<T>(val data: T? = null, val message: String? = null, val failure: Failure? = Failure.FAILURE) {
    class Success<T>(data: T) : Resource<T>(data)
    class Error<T>(failure: Failure, message: String? = null, data: T? = null) : Resource<T>(data, message, failure)
}