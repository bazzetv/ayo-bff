package com.terra.bff.client

import io.ktor.client.*
import io.ktor.client.engine.cio.*

// Singleton pour éviter les conflits
object HttpClientProvider {
    val client = HttpClient(CIO)
}

