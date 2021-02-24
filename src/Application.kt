package com.raf.ktor.demo

import com.raf.ktor.demo.handler.UserHandler
import com.raf.ktor.demo.model.User
import com.raf.ktor.demo.repository.UserRepository
import com.raf.ktor.demo.route.userRouting
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.response.*
import io.ktor.request.*
import io.ktor.routing.*
import io.ktor.serialization.*

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {
    install(ContentNegotiation) {
        json()
    }

    routing {
        userRouting(UserHandler(UserRepository()))
    }
}
