package ru.fitnessapp

import UsersTable
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.cio.*
import ru.fitnessapp.database.DatabaseConfig
import ru.fitnessapp.database.DatabaseFactory
import ru.fitnessapp.plugins.*

fun main() {
    embeddedServer(CIO, port = 8081, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
    UsersTable
}

fun Application.module() {
    DatabaseFactory.init(config = DatabaseConfig.MySqlConfig)
    configureSerialization()
    configureRouting()
}
