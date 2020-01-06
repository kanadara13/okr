package com.platform.component

import com.platform.com.platform.member.service.MemberFinder
import com.platform.member.service.MemberSaver

object ComponentFactory {

    fun init() {
        val memberSaver = MemberSaver()
        val memberFinder = MemberFinder()
    }
}
