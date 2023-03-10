package ru.fitnessapp.routes

import User
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import ru.fitnessapp.data.UserDao
import ru.fitnessapp.data.UserImpl
import ru.fitnessapp.domain.usecase.DeleteUser
import ru.fitnessapp.domain.usecase.GetUser
import ru.fitnessapp.domain.usecase.InsertUser
import ru.fitnessapp.domain.usecase.ValidateUser

fun Routing.userRoutes() {
    get("/user/{id}") {
        val id = call.parameters["id"]?.toInt()
        val result = GetUser(UserImpl()).invoke(id!!)

        val httpsStatus = if (result.errors.isEmpty()) HttpStatusCode.OK
        else result.errors.first().error.statusCode

        call.respond(
            message = result,
            status = httpsStatus,
        )
    }
    delete("/user/{id}") {
        val id = call.parameters["id"]?.toInt()

        val result = DeleteUser(UserImpl()).invoke(id!!)

        val httpsStatus = if (result.errors.isEmpty()) HttpStatusCode.OK
        else result.errors.first().error.statusCode

        call.respond(
            message = result,
            status = httpsStatus,
        )
    }
    post("/user/registration") {
        val user = call.receive<User>()

        val result = InsertUser(UserImpl()).invoke(user)

        val httpStatus = if (result.errors.isEmpty()) HttpStatusCode.Created else result.errors
            .first().error.statusCode

        call.respond(status = httpStatus, message = result)
    }
}