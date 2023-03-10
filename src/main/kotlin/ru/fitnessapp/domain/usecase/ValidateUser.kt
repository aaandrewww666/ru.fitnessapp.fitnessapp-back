package ru.fitnessapp.domain.usecase

import User
import ru.fitnessapp.utils.ErrorCode
import ru.fitnessapp.utils.ServiceResult

class ValidateUser {

    operator fun invoke(user: User): ServiceResult<Boolean> {
        return if (user.userLogin.isBlank() && user.passwordHash.isBlank()) {
            ServiceResult.Error(ErrorCode.BADLY_FORMATTED_INPUT)
        }
        else {
            if (ValidateLogin().invoke(user.userLogin)) ServiceResult.Success(true)
            else ServiceResult.Error(ErrorCode.INVALID_EMAIL)
        }
    }
}