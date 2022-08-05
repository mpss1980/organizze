package br.com.coupledev.organizze.domain.value_objects

import br.com.coupledev.organizze.domain.errors.ValueObjectError
import com.google.common.truth.Truth.assertThat
import org.junit.Test
import kotlin.test.assertFailsWith

class NameTest {

    @Test
    fun `should throw a BlankError when name is empty`() {
        val exception = assertFailsWith<ValueObjectError.BlankError>(
            block = { Name("")}
        )
        assertThat(exception).isNotNull()
        assertThat(exception.message).isEqualTo("The name can't be blank")
    }

    @Test
    fun `should throw a BlankError when name is blank`() {
        val exception = assertFailsWith<ValueObjectError.BlankError>(
            block = { Name(" ")}
        )
        assertThat(exception).isNotNull()
        assertThat(exception.message).isEqualTo("The name can't be blank")
    }

    @Test
    fun `should throw a OneWordError when name has one word`() {
        val exception = assertFailsWith<ValueObjectError.OneWordError>(
            block = { Name("Marcos")}
        )
        assertThat(exception).isNotNull()
        assertThat(exception.message).isEqualTo("The name have only one word")
    }
}