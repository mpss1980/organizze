package br.com.coupledev.organizze.core

abstract class Usecase<Param, T> {
    abstract suspend fun execute(input: Param): Resource<T>

    suspend operator fun invoke(input: Param) = execute(input)
}

abstract class UsecaseNoParam<T> {
    abstract suspend fun execute(): Resource<T>

    suspend operator fun invoke() = execute()
}