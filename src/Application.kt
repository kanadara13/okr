package com.platform

import com.platform.database.DatabaseFactory
import com.typesafe.config.ConfigFactory
import io.ktor.application.*
import io.ktor.config.HoconApplicationConfig
import io.ktor.response.*
import io.ktor.request.*
import io.ktor.server.engine.commandLineEnvironment
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty

fun main(args: Array<String>) {
    embeddedServer(Netty, commandLineEnvironment(args)).start()
}

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {
    DatabaseFactory.init(isDev)
}

val Application.env get() = environment.config.config("ktor.environment").property("environment").getString()
val Application.isLive get() = env == "live"
val Application.isDev get() = env != "live"


