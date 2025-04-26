package com.example.domain.user

class GetUserData() {
    fun getData(): UserData {
        return UserData(
            id = "1",
            name = "John Doe",
            avatar = ""
        )
    }
}