package com.noahseidman.adphone.delegates

import com.noahseidman.adphone.Services.Retrofit
import com.noahseidman.adphone.di.InjectionsUtils
import com.noahseidman.adphone.interfaces.LoadPosts
import com.noahseidman.adphone.models.UserData
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class LoadPostsImpl : LoadPosts {

    var disposable: Disposable? = null
    @Inject
    lateinit var retrofit: Retrofit

    init {
        InjectionsUtils.components.inject(this)
    }

    override fun loadPosts(
        authToken: String,
        user: String,
        onStart: () -> Unit,
        onFinish: (userData: UserData) -> Unit
    ) {
        disposable = retrofit.getUserMedia(authToken, user)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                onStart()
            }.onErrorResumeNext(Observable.empty())
            .delay(3, TimeUnit.SECONDS)//For effect only
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                onFinish(it)
            }, {
                //TODO add error state
            })
    }

    override fun cancelPostLoading() {
        disposable?.dispose()
    }
}