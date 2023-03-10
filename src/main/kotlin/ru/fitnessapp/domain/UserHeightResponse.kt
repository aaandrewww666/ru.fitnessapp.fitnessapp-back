package ru.fitnessapp.domain

import UserHeight

@kotlinx.serialization.Serializable
data class UserHeightResponse(val userHeight: UserHeight?, val errors: List<ResponseErrors> = emptyList())
