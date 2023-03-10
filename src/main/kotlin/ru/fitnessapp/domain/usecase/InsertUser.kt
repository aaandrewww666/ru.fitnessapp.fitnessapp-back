package ru.fitnessapp.domain.usecase

import User
import ru.fitnessapp.data.UserDao
import ru.fitnessapp.domain.ResponseErrors
import ru.fitnessapp.domain.UserResponse
import ru.fitnessapp.utils.ServiceResult

class InsertUser(private val userDao: UserDao){
    suspend operator fun invoke(userInf : User): UserResponse {
        return when (val validation = ValidateUser().invoke(userInf)) {
            is ServiceResult.Success -> {
                when (val result = userDao.emailExists(login = userInf.userLogin)) {
                    is ServiceResult.Success ->
                        when (val user = userDao.insertUser(userInf)) {
                            is ServiceResult.Success -> UserResponse(user.data, emptyList())
                            is ServiceResult.Error -> UserResponse(null, listOf(ResponseErrors(user.error, user.error.message)))
                        }
                    is ServiceResult.Error -> UserResponse(null, listOf(ResponseErrors(result.error, result.error.message)))
                }
            }
            is ServiceResult.Error -> UserResponse(null, listOf(ResponseErrors(validation.error, validation.error.message)))
        }
    }
}