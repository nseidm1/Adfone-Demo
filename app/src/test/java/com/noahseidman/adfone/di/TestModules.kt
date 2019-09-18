package com.noahseidman.adfone.di

import android.content.Context
import com.noahseidman.adfone.ExampleUnitTest
import com.noahseidman.adfone.services.Retrofit
import com.noahseidman.adfone.services.addMock
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class TestModules {

    @Provides
    @Singleton
    fun provideRetrofit(context: Context): Retrofit {
        val retrofit = Retrofit(context)
        retrofit.addMock()
        return retrofit
    }
}

@Singleton
@Component(modules = arrayOf(TestModules::class))
interface TestComponents: BaseComponents {
    fun inject(exampleUnitTests: ExampleUnitTest)
    @Component.Builder
    interface Builder {
        fun build(): TestComponents
        @BindsInstance
        fun context(context: Context): Builder
    }
}