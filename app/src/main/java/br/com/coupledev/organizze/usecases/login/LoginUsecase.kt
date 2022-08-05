package br.com.coupledev.organizze.usecases.login

import br.com.coupledev.organizze.core.Failure
import br.com.coupledev.organizze.core.Resource
import br.com.coupledev.organizze.core.Usecase
import br.com.coupledev.organizze.domain.entities.User
import br.com.coupledev.organizze.domain.repositories.UserRepository
import br.com.coupledev.organizze.domain.value_objects.Email
import br.com.coupledev.organizze.domain.value_objects.Password

class LoginUsecase(
    private val userRepository: UserRepository
) : Usecase<LoginInput, User>() {

    override suspend fun execute(input: LoginInput): Resource<User> {
        val errorMessage = "Invalid email or password"

        val email = try {
            Email(input.email)
        } catch (e: Error) {
            return Resource.Error(failure = Failure.INVALID_PARAMETERS, message = errorMessage)
        }

        val password = try {
            Password(input.password)
        } catch (e: Error) {
            return Resource.Error(failure = Failure.INVALID_PARAMETERS, message = errorMessage)
        }

        return when(val result = userRepository.login(email, password)) {
            is Resource.Error -> Resource.Error(failure = Failure.FAILURE, message = errorMessage)
            is Resource.Success -> result
        }
    }
}