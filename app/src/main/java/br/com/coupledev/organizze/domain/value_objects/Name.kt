package br.com.coupledev.organizze.domain.value_objects

import br.com.coupledev.organizze.domain.errors.ValueObjectError

data class Name(
    val value: String
) {
    init {
        if (value.isBlank()) {
            throw ValueObjectError.BlankError("The name can't be blank")
        }

        if (!value.contains(" ")) {
            throw ValueObjectError.OneWordError("The name have only one word")
        }
    }
}