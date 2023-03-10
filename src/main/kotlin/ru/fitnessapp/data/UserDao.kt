package ru.fitnessapp.data

import User
import ru.fitnessapp.utils.ServiceResult

interface UserDao {
    suspend fun getUser(Id : Int) : ServiceResult<User>
    suspend fun insertUser(user : User) : ServiceResult<User>
    suspend fun emailExists(login : String) : ServiceResult<Boolean>
    suspend fun deleteUser(id: Int) : ServiceResult<Boolean>
}