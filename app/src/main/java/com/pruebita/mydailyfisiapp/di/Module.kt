package com.pruebita.mydailyfisiapp.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
object AppModule {
    @Provides
    fun provideContext(@ApplicationContext context: Context): Context {
        return context
    }
}