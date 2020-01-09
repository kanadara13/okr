package handler

import com.fasterxml.jackson.databind.ObjectMapper
import com.google.gson.Gson
import com.platform.isDev
import com.platform.member.domain.NewMember
import com.platform.member.service.MemberSaver
import com.platform.router.member
import com.platform.serverInitializer
import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.config.MapApplicationConfig
import io.ktor.features.ContentNegotiation
import io.ktor.gson.gson
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import io.ktor.routing.routing
import io.ktor.server.testing.handleRequest
import io.ktor.server.testing.setBody
import io.ktor.server.testing.withTestApplication
import io.ktor.util.KtorExperimentalAPI
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.json
import kotlinx.serialization.stringify
import org.junit.Assert.assertEquals
import org.junit.Test
import java.text.DateFormat

@KtorExperimentalAPI
class MemberHandlerTest : TestApplicationBuilder() {

    @Test
    fun `class to json`(){
        val a = NewMember("a@a.com","외계인","12345!!")
        val parsed = Gson().toJson(a)
        println(parsed)
    }

    @Test
    fun testRequest() = withTestApplication({
        configApplication()
    }) {
        with(handleRequest(HttpMethod.Get, "/member")) {
            assertEquals(HttpStatusCode.OK, response.status())
        }

        val call = handleRequest(HttpMethod.Post, "/member") {
            addHeader(HttpHeaders.ContentType, ContentType.Application.Json.toString())
            setBody(Gson().toJson(NewMember("a@a.com","외계인","12345!!")))
        }

        with(call) {
            assertEquals(HttpStatusCode.Created, response.status())
            println(response.content)
        }
    }


}