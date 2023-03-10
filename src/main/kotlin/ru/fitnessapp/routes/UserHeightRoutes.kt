package ru.fitnessapp.routes

import UserHeight
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import ru.fitnessapp.data.UserHeightImpl
import ru.fitnessapp.data.UserImpl
import ru.fitnessapp.domain.usecase.*

fun Routing.userHeightRoutes() {
    get("/user/height/{id}") {
        val id = call.parameters["id"]?.toInt()
        val result = GetUserHeight(UserHeightImpl()).invoke(id!!)

        val httpsStatus = if (result.errors.isEmpty()) HttpStatusCode.OK
        else result.errors.first().error.statusCode

        call.respond(
            message = result,
            status = httpsStatus,
        )
    }

    delete("/user/height/{id}") {
        val id = call.parameters["id"]?.toInt()

        val result = DeleteUserHeight(UserHeightImpl()).invoke(id!!)
        val httpsStatus = if (result.errors.isEmpty()) HttpStatusCode.OK
        else result.errors.first().error.statusCode

        call.respond(
            message = result,
            status = httpsStatus,
        )
    }
    post("/user/height") {
        val userHeight = call.receive<UserHeight>()

        val result = InsertUserHeight(UserHeightImpl()).invoke(userHeight.userId,userHeight.height)

        val httpStatus = if (result.errors.isEmpty()) HttpStatusCode.Created else result.errors
            .first().error.statusCode

        call.respond(status = httpStatus, message = result)
    }
}