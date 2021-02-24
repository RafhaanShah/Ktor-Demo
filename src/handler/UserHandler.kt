package com.raf.ktor.demo.handler

import com.raf.ktor.demo.model.User
import com.raf.ktor.demo.repository.UserRepository
import com.raf.ktor.demo.route.PARAM_ID
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class UserHandler(private val userRepository: UserRepository) {

    suspend fun get(call: ApplicationCall) {
        val id = call.parameters[PARAM_ID]?.toIntOrNull() ?: return call.respond(HttpStatusCode.BadRequest)
        val user = userRepository.getUser(id) ?: return call.respond(HttpStatusCode.NotFound)
        call.respond(user)
    }

    suspend fun post(call: ApplicationCall) {
        try {
            val user = call.receive<User>()
            val id = userRepository.addUser(user)
            call.respond(HttpStatusCode.Created, Json.encodeToString(mapOf(PARAM_ID to id)))
        } catch (e: ContentTransformationException) {
            call.respond(HttpStatusCode.BadRequest)
        }
    }

    suspend fun delete(call: ApplicationCall) {

    }

}
