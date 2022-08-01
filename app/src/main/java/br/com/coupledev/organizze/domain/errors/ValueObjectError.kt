package br.com.coupledev.organizze.domain.errors

sealed class ValueObjectError(message: String?): Error(message) {
    class BlankError(message: String? = null): ValueObjectError(message)
    class InvalidError(message: String? = null): ValueObjectError(message)
    class OneWordError(message: String? = null): ValueObjectError(message)
    class TooShortError(message: String? = null): ValueObjectError(message)
}
