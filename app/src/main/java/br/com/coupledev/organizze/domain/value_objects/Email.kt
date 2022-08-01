package br.com.coupledev.organizze.domain.value_objects

import android.util.Patterns
import br.com.coupledev.organizze.domain.errors.ValueObjectError

data class Email(
    val value: String
) {
    init {
        if (value.isBlank()) {
            throw ValueObjectError.BlankError("The email can't be blank")
        }

        //todo: validar email de outra maaneira pra não usar testes instrumentados
        if (!Patterns.EMAIL_ADDRESS.matcher(value).matches()) {
            throw ValueObjectError.InvalidError("The email is invalid")
        }
    }
}