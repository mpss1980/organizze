package br.com.coupledev.organizze.domain.value_objects

import br.com.coupledev.organizze.domain.errors.ValueObjectError
import com.google.common.truth.Truth.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertFailsWith

@RunWith(JUnit4::class)
class EmailTest {
    @Test
    fun `should throw a BlankError when email is empty`() {
        val exception = assertFailsWith<ValueObjectError.BlankError>(
            block = { Email("")}
        )
        assertThat(exception).isNotNull()
        assertThat(exception.message).isEqualTo("The email can't be blank")
    }

    @Test
    fun `should throw a BlankError when email is blank`() {
        val exception = assertFailsWith<ValueObjectError.BlankError>(
            block = { Email(" ")}
        )
        assertThat(exception).isNotNull()
        assertThat(exception.message).isEqualTo("The email can't be blank")
    }

    @Test
    fun `should throw an InvalidError error when email don't have @`() {
        val exception = assertFailsWith<ValueObjectError.InvalidError>(
            block = { Email("marcosp.sousa.com")}
        )
        assertThat(exception).isNotNull()
        assertThat(exception.message).isEqualTo("The email is invalid")
    }

    @Test
    fun `should throw an InvalidError error when email starts with an special character`() {
        val exception = assertFailsWith<ValueObjectError.InvalidError>(
            block = { Email(".marcosp.sousa@gmail.com")}
        )
        assertThat(exception).isNotNull()
        assertThat(exception.message).isEqualTo("The email is invalid")
    }

    @Test
    fun `should throw an InvalidError error when email doesn't have any letter after @`() {
        val exception = assertFailsWith<ValueObjectError.InvalidError>(
            block = { Email("marcosp.sousa@")}
        )
        assertThat(exception).isNotNull()
        assertThat(exception.message).isEqualTo("The email is invalid")
    }

    @Test
    fun `should throw an InvalidError error when email doesn't have any dot after @`() {
        val exception = assertFailsWith<ValueObjectError.InvalidError>(
            block = { Email("marcosp.sousa@gmail")}
        )
        assertThat(exception).isNotNull()
        assertThat(exception.message).isEqualTo("The email is invalid")
    }
}