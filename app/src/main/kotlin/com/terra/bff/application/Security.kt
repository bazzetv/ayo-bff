package com.terra.bff.application

import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.client.*
import io.ktor.client.engine.apache.*
import io.ktor.http.*

fun Application.configureSecurity() {
    install(Authentication) {
        oauth("auth-oauth-google") {
            println("GOOGLE_CLIENT_ID: ${System.getenv("GOOGLE_CLIENT_ID")}")
            println("GOOGLE_CLIENT_SECRET: ${System.getenv("GOOGLE_CLIENT_SECRET")}")
            urlProvider = { "http://localhost:8080/auth/callback" }
            providerLookup = {
                OAuthServerSettings.OAuth2ServerSettings(
                    name = "google",
                    authorizeUrl = "https://accounts.google.com/o/oauth2/auth",
                    accessTokenUrl = "https://oauth2.googleapis.com/token",
                    requestMethod = HttpMethod.Post,
                    clientId = System.getenv("GOOGLE_CLIENT_ID"),
                    clientSecret = System.getenv("GOOGLE_CLIENT_SECRET"),
                    defaultScopes = listOf("profile", "email")
                )
            }
            client = HttpClient(Apache)
        }
    }
}