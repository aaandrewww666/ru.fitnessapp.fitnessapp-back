package ru.fitnessapp.domain.usecase

import ru.fitnessapp.data.UserImpl
import ru.fitnessapp.domain.ResponseErrors
import ru.fitnessapp.domain.UserResponse
import ru.fitnessapp.utils.ServiceResult

class GetUser(private val userDao: UserImpl) {
    suspend operator fun invoke(id: Int): UserResponse {
        return when (val user = userDao.getUser(id)) {
            is ServiceResult.Success -> UserResponse(user.data, emptyList())
            is ServiceResult.Error -> UserResponse(null, listOf(ResponseErrors(user.error, user.error.message)))
        }
    }
}