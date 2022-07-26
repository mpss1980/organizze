package br.com.coupledev.organizze.usecases.subscribe

import br.com.coupledev.organizze.core.Failure
import br.com.coupledev.organizze.core.Resource
import br.com.coupledev.organizze.domain.repositories.UserRepository
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class SubscribeUsecaseTest {
    private lateinit var userRepository: UserRepository
    private lateinit var usecase: SubscribeUsecase

    private val input = SubscribeInput(
        name = "Marcos Sousa",
        email = "marcosp.sousa@gmail.com",
        password = "abcd1234",
        repeatedPassword = "abcd1234"
    )

    @Before
    fun setup() {
        userRepository = mockk()
        usecase = SubscribeUsecase(userRepository)
    }

    @Test
    fun `should return true when success`()  = runBlocking {
        coEvery { userRepository.save(any()) } returns Resource.Success(true)

        val result = usecase.execute(input)

        assertThat(result.data).isEqualTo(true)
    }

    @Test
    fun `should return an error when name is invalid`()  = runBlocking {
        val result = usecase.execute(input.copy(name = " "))

        assertThat(result.failure).isEqualTo(Failure.INVALID_PARAMETERS)
    }

    @Test
    fun `should return an error when email is invalid`()  = runBlocking {
        val result = usecase.execute(input.copy(email = " "))

        assertThat(result.failure).isEqualTo(Failure.INVALID_PARAMETERS)
    }

    @Test
    fun `should return an error when password is invalid`()  = runBlocking {
        val result = usecase.execute(input.copy(password = "123"))

        assertThat(result.failure).isEqualTo(Failure.INVALID_PARAMETERS)
    }

    @Test
    fun `should return an error when repeated password is not equal to password`()  = runBlocking {
        val result = usecase.execute(input.copy(repeatedPassword = "123"))

        assertThat(result.failure).isEqualTo(Failure.INVALID_PARAMETERS)
        assertThat(result.message).isEqualTo("The repeated password doesn't match")
    }

    @Test
    fun `should return a failure when repository fails`()  = runBlocking {
        coEvery { userRepository.save(any()) } returns Resource.Error(Failure.FAILURE)

        val result = usecase.execute(input)

        assertThat(result.failure).isEqualTo(Failure.FAILURE)
    }
}