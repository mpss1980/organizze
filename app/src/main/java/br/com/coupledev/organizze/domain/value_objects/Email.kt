package br.com.coupledev.organizze.domain.value_objects

import br.com.coupledev.organizze.domain.errors.ValueObjectError

data class Email(
    val value: String
) {
    init {
        if (value.isBlank()) {
            throw ValueObjectError.BlankError("The email can't be blank")
        }

        val invalidEmail = !value.contains("@") || !value.first().isLetter()
                || value.split("@")[1].isEmpty() ||
                !value.split("@")[1].contains(".")

        if (invalidEmail) {
            throw ValueObjectError.InvalidError("The email is invalid")
        }
    }
}