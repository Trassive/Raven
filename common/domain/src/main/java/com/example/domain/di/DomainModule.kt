package com.example.domain.di

import com.example.domain.user.GetUserData
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class DomainModule {

    @Provides
    fun providesGetUserData(): GetUserData {
        return GetUserData()
    }
}