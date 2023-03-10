package ru.fitnessapp.domain.usecase

import ru.fitnessapp.data.UserWeightDao
import ru.fitnessapp.domain.DeleteResponse
import ru.fitnessapp.domain.ResponseErrors
import ru.fitnessapp.utils.ServiceResult
import java.time.LocalDate

class DeleteUserWeightByDate (private val userWeightDao: UserWeightDao) {
    suspend operator fun invoke(id: Int, date: String): DeleteResponse {
        return when (val result = userWeightDao.deleteUserWeightByDate(id, date)) {
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