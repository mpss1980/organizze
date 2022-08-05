package br.com.coupledev.organizze.domain.entities

import br.com.coupledev.organizze.domain.value_objects.Email
import br.com.coupledev.organizze.domain.value_objects.Name
import br.com.coupledev.organizze.domain.value_objects.Password

data class User(
    val name: Name,
    val email: Email,
    val password: Password,
)
