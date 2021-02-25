package com.raf.ktor.demo

import com.raf.ktor.demo.koin.userModule
import com.raf.ktor.demo.route.userRouting
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.routing.*
import io.ktor.serialization.*
import org.koin.ktor.ext.Koin

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {
    install(DefaultHeaders)
    install(CallLogging)
    install(ContentNegotiation) {
        json()
    }
    install(Koin) {
        modules(userModule)
    }

    routing {
        userRouting()
    }
}
