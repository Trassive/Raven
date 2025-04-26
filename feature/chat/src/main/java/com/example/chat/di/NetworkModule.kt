package com.example.chat.di

import android.content.Context
import com.example.chat.data.network.RestClient
import com.example.chat.data.network.WebSocketClient
import com.example.chat.data.remotedatasource.FirebaseFirestoreProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import javax.inject.Named
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {
    @Provides
    @Named(REST_CLIENT)
    fun provideRestClient(): HttpClient {
        return RestClient.client
    }
    @Provides
    @Named(WS_CLIENT)
    fun provideWebSocketClient(): HttpClient {
        return WebSocketClient.client
    }
    @Provides
    @Named(REST_URL_NAME)
    fun provideUrl(): String {
        return REST_URL
    }
    @Provides
    @Named(WS_URL_NAME)
    fun providesUrl(): String {
        return WS_URL
    }
    @Provides
    @Singleton
    fun providesFirebaseFirestoreProvider(@ApplicationContext context: Context): FirebaseFirestoreProvider {
        return FirebaseFirestoreProvider(context = context)
    }
}