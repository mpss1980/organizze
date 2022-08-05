package br.com.coupledev.organizze.data.datasource

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.coupledev.organizze.data.models.UserModel

@Database(
    entities = [UserModel::class],
    version = 1,
)
abstract class OrganizzeDatabase: RoomDatabase() {
    abstract val userDao: UserDao

    companion object {
        const val DATABASE_NAME = "organizze_db"
    }
}