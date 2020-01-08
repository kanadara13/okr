package com.platform

import com.platform.com.platform.member.service.MemberFinder
import com.platform.router.member
import com.platform.database.DatabaseFactory
import com.platform.member.service.MemberSaver
import io.ktor.application.*
import io.ktor.features.ContentNegotiation
import io.ktor.gson.gson
import io.ktor.routing.routing
import java.text.DateFormat

/*fun main(args: Array<String>) {
    embeddedServer(Netty, commandLineEnvironment(args)).start()
}*/

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {
    DatabaseFactory.init(isDev)
    val memberSaver = MemberSaver()
    val memberFinder = MemberFinder()

    install(ContentNegotiation) {
        gson {
            setDateFormat(DateFormat.LONG)
            setPrettyPrinting()
        }
    }

    routing {
        member(memberSaver = memberSaver, finder = memberFinder)
    }
}

val Application.env get() = environment.config.config("ktor.environment").property("environment").getString()
val Application.isLive get() = env == "live"
val Application.isDev get() = env != "live"


