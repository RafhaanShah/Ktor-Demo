package com.raf.ktor.demo.repository

import com.raf.ktor.demo.model.User
import java.util.concurrent.atomic.AtomicInteger

class UserRepository {

    private val storage = mutableMapOf<Int, User>()
    private val counter = AtomicInteger()

    fun addUser(user: User): Int {
        val id = counter.incrementAndGet()
        storage[id] = user
        return id
    }

    fun removeUser(id: Int) {
        storage.remove(id)
    }

    fun getUser(id: Int): User? {
        return storage[id]
    }

    fun userExists(id: Int): Boolean {
        return storage.containsKey(id)
    }

}
