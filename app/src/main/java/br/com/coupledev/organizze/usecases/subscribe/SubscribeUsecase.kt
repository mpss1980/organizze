package br.com.coupledev.organizze.usecases.subscribe

import br.com.coupledev.organizze.core.Failure
import br.com.coupledev.organizze.core.Resource
import br.com.coupledev.organizze.core.Usecase
import br.com.coupledev.organizze.domain.entities.User
import br.com.coupledev.organizze.domain.repositories.UserRepository
import br.com.coupledev.organizze.domain.value_objects.Email
import br.com.coupledev.organizze.domain.value_objects.Name
import br.com.coupledev.organizze.domain.value_objects.Password

class SubscribeUsecase(
    private val userRepository: UserRepository
) : Usecase<SubscribeInput, Boolean>() {

    override suspend fun execute(input: SubscribeInput): Resource<Boolean> {

        val name = try {
            Name(input.name)
        } catch (e: Error) {
            return Resource.Error(failure = Failure.INVALID_PARAMETERS, message = e.message)
        }

        val email = try {
            Email(input.email)
        } catch (e: Error) {
            return Resource.Error(failure = Failure.INVALID_PARAMETERS, message = e.message)
        }

        val password = try {
            Password(input.password)
        } catch (e: Error) {
            return Resource.Error(failure = Failure.INVALID_PARAMETERS, message = e.message)
        }

        if (password.value != input.repeatedPassword) {
            return Resource.Error(
                failure = Failure.INVALID_PARAMETERS,
                message = "The repeated password doesn't match"
            )
        }

        val user = User(name = name, email = email, password = password)

        return userRepository.save(user)
    }
}