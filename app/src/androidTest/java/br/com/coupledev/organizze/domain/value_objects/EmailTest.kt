package br.com.coupledev.organizze.domain.value_objects

import androidx.test.ext.junit.runners.AndroidJUnit4
import br.com.coupledev.organizze.domain.errors.ValueObjectError
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class EmailTest {

    @Test(expected = ValueObjectError.InvalidError::class)
    fun shouldThrowAnInvalidErrorWhenEmailIsInvalid() {
          Email("marcosp.sousa")
    }
}