package ru.fitnessapp.data.models

@kotlinx.serialization.Serializable
data class UserDate (
                     val userId: Int,
                     val date : String
                     )