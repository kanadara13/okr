package com.platform.router

import com.platform.com.platform.member.service.MemberFinder
import com.platform.member.domain.NewMember
import com.platform.member.service.MemberSaver
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.routing.route

fun Route.member(memberSaver: MemberSaver, finder : MemberFinder) {
    route("/member") {
        get {
            call.respond(HttpStatusCode.OK, "members")
        }
        post {
            val request = call.receive<NewMember>()
            call.respond(HttpStatusCode.Created, memberSaver.save(request))
        }
    }
}
