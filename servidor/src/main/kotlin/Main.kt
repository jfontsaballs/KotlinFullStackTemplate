package com.test

import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

fun main() {
    var keepRunning = true
    var counter = 0

    val server = embeddedServer(Netty, port = 8080) {
        routing {
            get("/") {
                call.respondText("Hello, world!\n$counter")
            }
            get("/stop") {
                keepRunning = false
                call.respondText("Stopping")
            }
        }
    }.start()

    runBlocking {
        while (keepRunning) {
            println("Working ${counter++}")
            delay(500)
        }
    }

    server.stop(1000, 3000)
}