package ru.fitnessapp.domain

import ru.fitnessapp.utils.ErrorCode

@kotlinx.serialization.Serializable
data class ResponseErrors(val error: ErrorCode, val message: String)