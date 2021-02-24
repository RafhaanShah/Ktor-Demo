package com.raf.ktor.demo.model

import kotlinx.serialization.Serializable

@Serializable
data class User(val firstName: String, val lastName: String, val email: String)