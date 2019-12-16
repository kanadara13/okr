package com.platform.member.service

import com.platform.member.domain.NewMember
import com.platform.member.entity.Member
import com.platform.member.entity.MemberGroup
import org.jetbrains.exposed.sql.transactions.transaction
import org.joda.time.DateTime
import java.time.LocalDateTime

class MemberSaver(){

    fun save(new : NewMember): Member {
        val member = Member(0)
        val now =  DateTime.now()
        transaction {
            val member = Member.new {
                this.email = new.email
                this.name = new.name
                this.password = new.password
                this.createdAt = now
                this.updatedAt = now
            }
            MemberGroup.new {
                this.memberNo = member.id.value
                this.groupName = "${member.name} 의 개인"
                this.createdAt = now
                this.updatedAt = now
            }
        }
        return member
    }
}