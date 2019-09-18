package com.noahseidman.adfone.services

import android.content.Context
import com.noahseidman.adfone.R
import com.noahseidman.adfone.models.UserData
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

open class Retrofit(context: Context) {

    var client: Retrofit

    init {
        val builder = Retrofit.Builder()
            .baseUrl(context.getString(R.string.base_url))
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        client = builder.build()
    }

    fun getUserMedia(authToken: String, user: String): Observable<UserData> {
        val mediaService = client.create(MediaService::class.java)
        return mediaService.getUserMedia(user, authToken)
    }
}

interface MediaService {
    @GET("v1/users/{user}/media/recent")
    fun getUserMedia(@Path("user") user: String, @Query("access_token") authToken: String): Observable<UserData>
}