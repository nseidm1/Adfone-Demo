package com.noahseidman.adphone.di

import com.noahseidman.adphone.delegates.LoadPostsImpl
import com.noahseidman.adphone.fragments.InstagramFragment

interface BaseComponents {
    fun inject(instagramFragment: InstagramFragment)
    fun inject(loadPostsImpl: LoadPostsImpl)
}