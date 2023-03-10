package ru.fitnessapp.domain.usecase

import ru.fitnessapp.data.UserWeightDao
import ru.fitnessapp.domain.ResponseErrors
import ru.fitnessapp.domain.UserWeightsResponse
import ru.fitnessapp.utils.ServiceResult

class GetUserWeights (private val userWeightDao: UserWeightDao) {
    suspend operator fun invoke(id: Int): UserWeightsResponse {
        return when (val userWeight = userWeightDao.getUserWeights(id)) {
            is ServiceResult.Success -> UserWeightsResponse(userWeight.data, emptyList())
            is ServiceResult.Error -> UserWeightsResponse(null,
                listOf(ResponseErrors(userWeight.error, userWeight.error.message)))
        }
    }
}