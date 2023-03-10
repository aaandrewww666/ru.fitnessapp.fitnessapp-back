package ru.fitnessapp.domain.usecase

import ru.fitnessapp.data.UserWeightImpl
import ru.fitnessapp.domain.ResponseErrors
import ru.fitnessapp.domain.UserWeightResponse
import ru.fitnessapp.utils.ServiceResult

class InsertUserWeight(private val userWeightImpl: UserWeightImpl) {
    suspend operator fun invoke(userId: Int, weight: Double): UserWeightResponse {
        return when (val userWeight = userWeightImpl.insertUserWeight(userId, weight)) {
            is ServiceResult.Success -> UserWeightResponse(userWeight.data, emptyList())
            is ServiceResult.Error -> UserWeightResponse(
                null,
                listOf(ResponseErrors(userWeight.error, userWeight.error.message))
            )
        }
    }
}