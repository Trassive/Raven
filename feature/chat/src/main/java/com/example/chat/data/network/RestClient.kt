package com.example.chat.data.network

import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json

object RestClient{
    val client = HttpClient(OkHttp){
        install(ContentNegotiation){
            json()
        }
    }
}