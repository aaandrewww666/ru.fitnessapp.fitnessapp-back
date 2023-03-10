package ru.fitnessapp.domain.usecase

import ru.fitnessapp.data.UserHeightImpl
import ru.fitnessapp.domain.ResponseErrors
import ru.fitnessapp.domain.UserHeightResponse
import ru.fitnessapp.utils.ServiceResult

class InsertUserHeight(private val userHeightDao: UserHeightImpl){
    suspend operator fun invoke(userId : Int, height : Double): UserHeightResponse {
        return when (val userHeight = userHeightDao.insertUserHeight(userId, height)) {
            is ServiceResult.Success -> UserHeightResponse(userHeight.data, emptyList())
            is ServiceResult.Error -> UserHeightResponse(null,
                listOf(ResponseErrors(userHeight.error, userHeight.error.message)))
        }
    }
}