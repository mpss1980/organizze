package br.com.coupledev.organizze.usecases.subscribe

import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class SubscribeUsecaseTest {

    private lateinit var usecase: SubscribeUsecase

    private val input = SubscribeInput(
        name = "Name",
        email = "marcosp.sousa@gmail.com",
        password = "abc123"
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
}