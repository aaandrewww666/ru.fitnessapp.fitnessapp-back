package ru.fitnessapp.domain.usecase

import ru.fitnessapp.data.UserWeightDao
import ru.fitnessapp.domain.DeleteResponse
import ru.fitnessapp.domain.ResponseErrors
import ru.fitnessapp.utils.ServiceResult

class DeleteUserWeights (private val userWeightDao: UserWeightDao) {
    suspend operator fun invoke(id: Int): DeleteResponse {
        return when (val result = userWeightDao.deleteUserWeights(id)) {
            is ServiceResult.Success -> DeleteResponse(success = true)
            is ServiceResult.Error -> DeleteResponse(
                errors = listOf(
                    ResponseErrors(
                        result.error,
                        result.error.message
                    )
                )
            )
        }
    }
}