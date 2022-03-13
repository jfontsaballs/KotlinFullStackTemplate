package com.test

import io.ktor.application.*
import io.ktor.html.*
import io.ktor.http.content.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.html.*

fun main() {
    var keepRunning = true
    var counter = 0

    val server = embeddedServer(Netty, port = 8080) {
        routing {
            get("/") {
                call.respondHtml {
                    head {
                        meta { charset = "utf-8" }
                        title { +"Kotlin full stack application demo" }
                    }
                    body {
                        div {
                            id = "root"
                            div {
                                style = "display: flex; justify-content: center;"
                                p {
                                    style = "margin: 10%; font-size: 48px;"
                                    +"Loading..."
                                }
                            }
                        }
                        script(src = "/client.js") { }
                    }
                }
            }
            get("/stop") {
                keepRunning = false
                call.respondText("Stopping")
            }

            static("/") {
                resources("/")
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