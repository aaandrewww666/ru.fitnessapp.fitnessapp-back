package ru.fitnessapp.domain

import User

@kotlinx.serialization.Serializable
data class UserResponse(val user: User?, val errors: List<ResponseErrors> = emptyList())

@kotlinx.serialization.Serializable
data class DeleteResponse(val success: Boolean = false, val errors: List<ResponseErrors> = emptyList())

