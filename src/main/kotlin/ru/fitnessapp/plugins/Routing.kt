package ru.fitnessapp.plugins

import io.ktor.server.routing.*
import io.ktor.server.application.*
import ru.fitnessapp.routes.userHeightRoutes
import ru.fitnessapp.routes.userRoutes
import ru.fitnessapp.routes.userWeightRoutes

fun Application.configureRouting() {
    routing {
        userRoutes()
        userHeightRoutes()
        userWeightRoutes()
    }
}
