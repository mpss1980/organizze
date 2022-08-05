package br.com.coupledev.organizze.domain.repositories

import br.com.coupledev.organizze.core.Resource
import br.com.coupledev.organizze.domain.entities.User
import br.com.coupledev.organizze.domain.value_objects.Email
import br.com.coupledev.organizze.domain.value_objects.Password

interface UserRepository {
    suspend fun login(email: Email, password: Password): Resource<User>
    suspend fun save(user: User): Resource<Boolean>
}