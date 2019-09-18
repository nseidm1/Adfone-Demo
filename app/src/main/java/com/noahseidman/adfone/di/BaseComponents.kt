package com.noahseidman.adfone.di

import com.noahseidman.adfone.delegates.LoadPostsImpl
import com.noahseidman.adfone.fragments.InstagramFragment

interface BaseComponents {
    fun inject(instagramFragment: InstagramFragment)
    fun inject(loadPostsImpl: LoadPostsImpl)
}