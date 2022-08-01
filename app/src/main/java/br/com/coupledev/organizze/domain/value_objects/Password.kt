package br.com.coupledev.organizze.domain.value_objects

import br.com.coupledev.organizze.domain.errors.ValueObjectError

data class Password(
    val value: String
) {
    init {
        if (value.length < 8) {
            throw ValueObjectError.TooShortError("The password need to consist of at least 8 characters")
        }

        val containsLettersAndDigits = value.any { it.isDigit() } && value.any { it.isLetter() }
        if (!containsLettersAndDigits) {
            throw ValueObjectError.InvalidError("The password needs to contain at least one letter and one digit")
        }
    }
}