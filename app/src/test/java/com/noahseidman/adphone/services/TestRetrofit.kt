package com.noahseidman.adphone.services

import android.content.Context
import com.appham.mockinizer.Method
import com.appham.mockinizer.RequestFilter
import com.appham.mockinizer.mockinize
import com.google.gson.Gson
import com.noahseidman.adphone.Services.Retrofit
import com.noahseidman.adphone.models.*
import com.noahseidman.adphone.utils.SameThreadExecutorService
import okhttp3.Dispatcher
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class TestRetrofit(context: Context): Retrofit(context) {


}

fun Retrofit.addMock() {
    val mocks: Map<RequestFilter, MockResponse> = mapOf(
        RequestFilter(
            path = "/v1/users/1621043365/media/recent",
            method = Method.GET,
            body = null
        ) to MockResponse().apply {
            setResponseCode(200)
            val postDatas = mutableListOf<PostData>().apply {
                for (i in 1..20) {
                    add(
                        PostData(
                        images = Images(
                            thumbnail = Thumbnail(
                                width = 0,
                                height = 0,
                                url = ""
                            ),
                            low_resolution = LowResolution(
                                width = 0,
                                height = 0,
                                url = ""
                            ),
                            standard_resolution = StandardResolution(
                                width = 0,
                                height = 0,
                                url = ""
                            )
                        ),
                        caption = Caption(
                            from = From(
                                profile_picture = "",
                                username = ""
                            ),
                            text = ""
                        )
                    ))
                }
            }
            val userData = UserData(postDatas.toTypedArray())
            setBody(Gson().toJson(userData))
        }
    )

    val okClient = OkHttpClient.Builder().dispatcher(Dispatcher(SameThreadExecutorService())).mockinize(mocks).build()

    client = retrofit2.Retrofit.Builder()
        .baseUrl("https://api.instagram.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .callbackExecutor { it.run() }
        .client(okClient).build()
}