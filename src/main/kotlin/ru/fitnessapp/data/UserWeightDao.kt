package ru.fitnessapp.data

import ru.fitnessapp.data.models.UserWeight
import ru.fitnessapp.utils.ServiceResult
import java.time.LocalDate

interface UserWeightDao {
    suspend fun getUserWeights(id : Int) : ServiceResult<List<UserWeight>>
    suspend fun insertUserWeight(userId : Int, weight : Double) : ServiceResult<UserWeight>
    suspend fun deleteUserWeights(id: Int) : ServiceResult<Boolean>
    suspend fun deleteUserWeightByDate(id : Int, date : String) : ServiceResult<Boolean>
}