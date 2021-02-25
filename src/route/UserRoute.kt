package com.raf.ktor.demo.route

import com.raf.ktor.demo.handler.UserHandler
import io.ktor.application.*
import io.ktor.routing.*
import org.koin.ktor.ext.inject

const val PARAM_ID = "id"

fun Route.userRouting() {

    val userHandler by inject<UserHandler>()

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
