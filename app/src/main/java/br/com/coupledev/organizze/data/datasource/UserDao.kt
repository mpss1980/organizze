package br.com.coupledev.organizze.data.datasource

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.com.coupledev.organizze.data.models.UserModel

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(userModel: UserModel)

    @Query("SELECT * FROM users WHERE email = :email AND password = :password")
    suspend fun findUserByEmailAndPassword(email: String, password: String): UserModel?

}