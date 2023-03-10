package ru.fitnessapp.domain.usecase

import ru.fitnessapp.data.UserDao
import ru.fitnessapp.domain.DeleteResponse
import ru.fitnessapp.domain.ResponseErrors
import ru.fitnessapp.utils.ServiceResult

class DeleteUser(private val userDao: UserDao) {
    suspend operator fun invoke(id: Int): DeleteResponse {
        return when (val result = userDao.deleteUser(id)) {
            is ServiceResult.Success -> DeleteResponse(success = true)
            is ServiceResult.Error -> DeleteResponse(errors = listOf(ResponseErrors(result.error, result.error.message)))
        }
    }
}