package br.com.coupledev.organizze.usecases.subscribe

import br.com.coupledev.organizze.core.Resource
import br.com.coupledev.organizze.core.Usecase

class SubscribeUsecase: Usecase<SubscribeInput, Boolean>() {

    override suspend fun execute(input: SubscribeInput): Resource<Boolean> {
        TODO("Not yet implemented")
    }
}