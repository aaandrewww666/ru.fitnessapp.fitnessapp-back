package ru.fitnessapp.domain.usecase

import ru.fitnessapp.data.UserHeightDao
import ru.fitnessapp.domain.DeleteResponse
import ru.fitnessapp.domain.ResponseErrors
import ru.fitnessapp.utils.ServiceResult

class DeleteUserHeight(private val userHeightDao: UserHeightDao) {
    suspend operator fun invoke(id: Int): DeleteResponse {
        return when (val result = userHeightDao.deleteUserHeight(id)) {
            is ServiceResult.Success -> DeleteResponse(success = true)
            is ServiceResult.Error -> DeleteResponse(errors = listOf(ResponseErrors(result.error, result.error.message)))
        }
    }
}