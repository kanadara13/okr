package handler

import com.platform.serverInitializer
import io.ktor.application.Application
import io.ktor.config.MapApplicationConfig
import io.ktor.server.testing.withTestApplication
import io.ktor.util.KtorExperimentalAPI

@KtorExperimentalAPI
open class TestApplicationBuilder {

    fun Application.configApplication() {
        val config = environment.config as MapApplicationConfig
        val env = config.property("ktor.deployment.environment").getString()
        serverInitializer(env != "live")
    }
}
