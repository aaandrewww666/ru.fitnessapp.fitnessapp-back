package ru.fitnessapp.routes

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import ru.fitnessapp.data.UserWeightImpl
import ru.fitnessapp.data.models.UserDate
import ru.fitnessapp.data.models.UserWeight
import ru.fitnessapp.domain.usecase.DeleteUserWeightByDate
import ru.fitnessapp.domain.usecase.DeleteUserWeights
import ru.fitnessapp.domain.usecase.GetUserWeights
import ru.fitnessapp.domain.usecase.InsertUserWeight

fun Routing.userWeightRoutes() {
    post("user/weight") {
        //post-запрос на добавление записи о весе пользователя в таблицу usersweight
        val userWeight = call.receive<UserWeight>()//получение и сериализация объекта класса UserWeight из json,
        //отправленный клиентом

        val result = InsertUserWeight(UserWeightImpl()).invoke(
            userWeight.userId,
            userWeight.weight
        ) //получение ответа о выполнении запроса о результатах добавления

        val httpStatus = if (result.errors.isEmpty()) HttpStatusCode.Created else result.errors
            .first().error.statusCode //код ответа

        call.respond(status = httpStatus, message = result) //возврат кода и сообщения клиенту
    }

    delete("user/weight/{id}") {
        //delete-запрос на удаление всех записей определённого пользователя (по userId)
        val user = call.parameters["id"]!!.toInt() //получение userId из параметра запроса

        val result = DeleteUserWeights(UserWeightImpl()).invoke(user) //получение ответа о выполнении удаления записей, связанных с пользователем

        val httpStatus = if (result.errors.isEmpty()) HttpStatusCode.Created else result.errors
            .first().error.statusCode

        call.respond(status = httpStatus, message = result)
    }

    delete("user/weight/date") {
        //delete-запрос на удаление записи об определённом пользователе в определённую дату
        val userWeight = call.receive<UserDate>() //получение и сериализация объекта класса UserDate из json,
        //отправленный клиентом

        val result = DeleteUserWeightByDate(UserWeightImpl()).invoke(userWeight.userId, userWeight.date) //удаление данных

        val httpStatus = if (result.errors.isEmpty()) HttpStatusCode.Created else result.errors
            .first().error.statusCode

        call.respond(status = httpStatus, message = result)
    }

    get("user/weight/{id}") {
        //get-запрос на получение клиентом списка информации о весе конкретного пользователя
        val userId = call.parameters["id"]!!.toInt()

        val result = GetUserWeights(UserWeightImpl()).invoke(userId) //получение ответа о выполнении запроса по получению данных

        val httpStatus = if (result.errors.isEmpty()) HttpStatusCode.Created else result.errors
            .first().error.statusCode

        call.respond(status = httpStatus, message = result)
    }
}