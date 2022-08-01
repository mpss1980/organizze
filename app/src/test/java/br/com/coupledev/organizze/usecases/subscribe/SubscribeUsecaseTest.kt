package br.com.coupledev.organizze.usecases.subscribe

import br.com.coupledev.organizze.core.Failure
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class SubscribeUsecaseTest {

    private lateinit var usecase: SubscribeUsecase

    private val input = SubscribeInput(
        name = "Marcos Sousa",
        email = "marcosp.sousa@gmail.com",
        password = "abcd1234",
        repeatedPassword = "abcd1234"
    )

    @Before
    fun setup() {
        usecase = SubscribeUsecase()
    }

    @Test
    fun `should return true when success`()  = runBlocking {
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
}