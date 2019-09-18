package com.noahseidman.adfone.interfaces

import com.noahseidman.adfone.models.UserData

interface LoadPosts {

    fun loadPosts(
        authToken: String,
        user: String,
        onStart: () -> Unit,
        onFinish: (userData: UserData) -> Unit
    )

    fun cancelPostLoading()
}