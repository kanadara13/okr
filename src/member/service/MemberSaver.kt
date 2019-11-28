package com.platform.member.service

import com.platform.member.domain.NewMember
import com.platform.member.entity.Member
import org.jetbrains.exposed.sql.transactions.transaction

class MemberSaver(){

    fun save(new : NewMember) {
        transaction {
            Member.new {
                this.email = new.email
                this.name = new.name
                this.password = new.password
            }
        }
    }
}