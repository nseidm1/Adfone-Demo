package com.noahseidman.adphone.di

import android.content.Context
import com.noahseidman.adphone.Services.Retrofit
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class Modules {

    @Provides
    @Singleton
    fun provideRetrofit(context: Context): Retrofit {
        return Retrofit(context);
    }
}

@Singleton
@Component(modules = [Modules::class])
interface Components : BaseComponents {
    @Component.Builder
    interface Builder {
        fun build(): Components
        @BindsInstance
        fun context(context: Context): Builder
    }
}
