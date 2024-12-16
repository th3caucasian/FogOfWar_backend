package com.example.plugins

import com.example.cache.InMemoryCache
import com.example.cache.TokenCache
import com.example.features.login.LoginReceiveRemote
import com.example.features.login.LoginResponseRemote
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class Test(
    val text: String
)


fun Application.configureRouting() {
    routing {
        get("/") {
            call.respond(Test(text = "Hello world"))
        }
    }
}
