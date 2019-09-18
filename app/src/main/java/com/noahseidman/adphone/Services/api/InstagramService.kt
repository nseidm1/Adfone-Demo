package com.noahseidman.adphone.Services.api

import retrofit2.http.GET
import retrofit2.http.Path

interface InstagramService {

    @GET("oauth/authorize/?client_id={client_id}&redirect_uri=REDIRECT-URI&response_type=token")
    fun auth(@Path("client_id") clientId: String)
}