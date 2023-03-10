package ru.fitnessapp.data.models

import UsersHeightTable.integer
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.Table.Dual.primaryKey
import org.jetbrains.exposed.sql.javatime.date
import java.io.Serializable
import java.time.LocalDate

@kotlinx.serialization.Serializable
data class UserWeight(
    val userId: Int,
    val weight : Double,
    val date : String
) : Serializable

object  UsersWeightTable : Table("users_weight") {
    val userId: Column<Int> = integer("userId")
    val weight: Column<Double> = double("weight")
    val date = date("date").clientDefault { LocalDate.now() }

    override val primaryKey = PrimaryKey(userId, date)
}