package com.example.mvvm_demo.model

import kotlinx.coroutines.delay

class UserRepository {
    suspend fun fetchUser(): User {
        delay(2000)
        return User("Abhishek", 22)
    }
}
