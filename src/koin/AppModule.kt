package com.raf.ktor.demo.koin

import com.raf.ktor.demo.handler.UserHandler
import com.raf.ktor.demo.repository.UserRepository
import org.koin.dsl.module

val userModule = module {

    factory { UserHandler(get()) }

    single { UserRepository() }

}
