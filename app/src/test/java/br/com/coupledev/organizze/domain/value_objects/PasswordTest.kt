package br.com.coupledev.organizze.domain.value_objects

import br.com.coupledev.organizze.domain.errors.ValueObjectError
import com.google.common.truth.Truth.assertThat
import org.junit.Test
import kotlin.test.assertFailsWith

class PasswordTest {

    @Test
    fun `should return a TooShortError when password length is shorter than 8`() {
        val exception = assertFailsWith<ValueObjectError.TooShortError>(
            block = { Password("1ab1")}
        )
        assertThat(exception).isNotNull()
        assertThat(exception.message).isEqualTo("The password need to consist of at least 8 characters")
    }

    @Test
    fun `should return a InvalidError when password doesn't have any digit`() {
        val exception = assertFailsWith<ValueObjectError.InvalidError>(
            block = { Password("abcdfghitjklm")}
        )
        assertThat(exception).isNotNull()
        assertThat(exception.message).isEqualTo("The password needs to contain at least one letter and one digit")
    }

    @Test
    fun `should return a InvalidError when password doesn't have any letter`() {
        val exception = assertFailsWith<ValueObjectError.InvalidError>(
            block = { Password("0123456789")}
        )
        assertThat(exception).isNotNull()
        assertThat(exception.message).isEqualTo("The password needs to contain at least one letter and one digit")
    }

}