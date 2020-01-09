package com.platform

import com.fasterxml.jackson.databind.SerializationFeature
import com.platform.router.member
import com.platform.database.DatabaseFactory
import com.platform.member.service.MemberFinder
import com.platform.member.service.MemberSaver
import com.platform.objectives.service.ObjectiveSaver
import com.platform.router.objectives
import io.ktor.application.*
import io.ktor.features.ContentNegotiation
import io.ktor.gson.gson
import io.ktor.jackson.jackson
import io.ktor.routing.routing
import java.text.DateFormat

/*fun main(args: Array<String>) {
    embeddedServer(Netty, commandLineEnvironment(args)).start()
}*/

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {
    serverInitializer(isDev)
}

val Application.env get() = environment.config.config("ktor.environment").property("environment").getString()
val Application.isLive get() = env == "live"
val Application.isDev get() = env != "live"

fun Application.serverInitializer(isDev : Boolean) {
    DatabaseFactory.init(isDev)
    val memberSaver = MemberSaver()
    val memberFinder = MemberFinder()
    val objectiveSaver = ObjectiveSaver()

    install(ContentNegotiation) {
        gson {
            setDateFormat(DateFormat.LONG)
            setPrettyPrinting()
        }
        jackson {
            enable(SerializationFeature.INDENT_OUTPUT)
        }
    }

    routing {
        member(memberSaver = memberSaver, finder = memberFinder)
        objectives(objectiveSaver)
    }
}




