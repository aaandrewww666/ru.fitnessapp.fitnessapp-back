package ru.fitnessapp.domain.usecase

import ru.fitnessapp.data.UserHeightDao
import ru.fitnessapp.domain.ResponseErrors
import ru.fitnessapp.domain.UserHeightResponse
import ru.fitnessapp.utils.ServiceResult

class GetUserHeight (private val userHeightDao: UserHeightDao) {
    suspend operator fun invoke(id: Int): UserHeightResponse {
        return when (val userHeight = userHeightDao.getUserHeight(id)) {
            is ServiceResult.Success -> UserHeightResponse(userHeight.data, emptyList())
            is ServiceResult.Error -> UserHeightResponse(null,
                listOf(ResponseErrors(userHeight.error, userHeight.error.message)))
        }
    }
}