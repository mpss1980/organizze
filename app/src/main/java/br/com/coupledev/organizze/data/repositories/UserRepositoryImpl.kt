package br.com.coupledev.organizze.data.repositories

import br.com.coupledev.organizze.core.Failure
import br.com.coupledev.organizze.core.Resource
import br.com.coupledev.organizze.data.datasource.UserDao
import br.com.coupledev.organizze.data.models.UserModel
import br.com.coupledev.organizze.domain.entities.User
import br.com.coupledev.organizze.domain.repositories.UserRepository
import br.com.coupledev.organizze.domain.value_objects.Email
import br.com.coupledev.organizze.domain.value_objects.Name
import br.com.coupledev.organizze.domain.value_objects.Password

class UserRepositoryImpl(
    private val dao: UserDao
) : UserRepository {
    override suspend fun login(email: Email, password: Password): Resource<User> {
        dao.findUserByEmailAndPassword(email = email.value, password = password.value).also { userModel ->
            if (userModel != null) {
                val user = User(
                    name = Name(userModel.name),
                    email = Email(userModel.email),
                    password = Password(userModel.password)
                )
                Resource.Success(user)
            } else {
                Resource.Error(Failure.FAILURE)
            }
        }
    }//todo: ver o que é este erro e a melhor maneira de lidar (usar try ou usar also)

    override suspend fun save(user: User): Resource<Boolean> {
        try {
            val userModel = UserModel(
                name = user.name.value,
                email = user.email.value,
                password = user.password.value
            )
            dao.insertUser(userModel)

        } catch (e: )
    }
}