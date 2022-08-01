package br.com.coupledev.organizze.domain.value_objects

import br.com.coupledev.organizze.domain.errors.ValueObjectError
import com.google.common.truth.Truth
import org.junit.Test
import kotlin.test.assertFailsWith

class EmailTest {
    @Test
    fun `should return a BlankError when email is empty`() {
        val exception = assertFailsWith<ValueObjectError.BlankError>(
            block = { Email("")}
        )
        Truth.assertThat(exception).isNotNull()
        Truth.assertThat(exception.message).isEqualTo("The email can't be blank")
    }

    @Test
    fun `should return a BlankError when email is blank`() {
        val exception = assertFailsWith<ValueObjectError.BlankError>(
            block = { Email(" ")}
        )
        Truth.assertThat(exception).isNotNull()
        Truth.assertThat(exception.message).isEqualTo("The email can't be blank")
    }
}