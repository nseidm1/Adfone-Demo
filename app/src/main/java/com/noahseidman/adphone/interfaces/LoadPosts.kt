package com.noahseidman.adphone.interfaces

import com.noahseidman.adphone.models.UserData

interface LoadPosts {

    fun loadPosts(
        authToken: String,
        user: String,
        onStart: () -> Unit,
        onFinish: (userData: UserData) -> Unit
    )

    fun cancelPostLoading()
}