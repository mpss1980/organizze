package br.com.coupledev.organizze.usecases.login

import br.com.coupledev.organizze.core.Failure
import br.com.coupledev.organizze.core.Resource
import br.com.coupledev.organizze.domain.entities.User
import br.com.coupledev.organizze.domain.repositories.UserRepository
import br.com.coupledev.organizze.domain.value_objects.Email
import br.com.coupledev.organizze.domain.value_objects.Name
import br.com.coupledev.organizze.domain.value_objects.Password
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class LoginUsecaseTest{
    private lateinit var userRepository: UserRepository
    private lateinit var usecase: LoginUsecase

    private val input = LoginInput(
        email = "marcosp.sousa@gmail.com",
        password = "abcd1234",
    )

    private val user = User(
        name = Name("Marcos Sousa"),
        email = Email("marcosp.sousa@gmail.com"),
        password = Password("abcd1234")
    )

    @Before
    fun setup() {
        userRepository = mockk()
        usecase = LoginUsecase(userRepository)
    }

    @Test
    fun `should return an user when success`()  = runBlocking {
        coEvery { userRepository.login(any(), any()) } returns Resource.Success(user)

        val result = usecase.execute(input)

        assertThat(result.data).isEqualTo(user)
    }

    @Test
    fun `should return an error when email is invalid`()  = runBlocking {
        val result = usecase.execute(input.copy(email = " "))

        assertThat(result.failure).isEqualTo(Failure.INVALID_PARAMETERS)
        assertThat(result.message).isEqualTo("Invalid email or password")
    }

    @Test
    fun `should return an error when password is invalid`()  = runBlocking {
        val result = usecase.execute(input.copy(password = "123"))

        assertThat(result.failure).isEqualTo(Failure.INVALID_PARAMETERS)
        assertThat(result.message).isEqualTo("Invalid email or password")
    }

    @Test
    fun `should return a failure when repository fails`()  = runBlocking {
        coEvery { userRepository.login(any(), any()) } returns Resource.Error(Failure.FAILURE)

        val result = usecase.execute(input)

        assertThat(result.failure).isEqualTo(Failure.FAILURE)
        assertThat(result.message).isEqualTo("Invalid email or password")
    }
}