package com.raf.ktor.demo.route

import com.raf.ktor.demo.handler.UserHandler
import com.raf.ktor.demo.repository.UserRepository
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import kotlin.text.get

const val PARAM_ID = "id"

fun Route.userRouting(userHandler: UserHandler) {
    route("/user") {
        get("{$PARAM_ID}") {
            userHandler.get(call)
        }
        post {
            userHandler.post(call)
        }
        delete("{$PARAM_ID}") {

        }
    }
}

