package com.platform.member.service

import com.platform.member.domain.NewMember
import com.platform.member.entity.Member
import com.platform.member.entity.MemberGroup
import kotlinx.coroutines.runBlocking
import org.jetbrains.exposed.sql.transactions.transaction
import org.joda.time.DateTime
import java.time.LocalDateTime

class MemberSaver{

    suspend fun save(new : NewMember): Member =
        transaction {
            val member = Member.new {
                this.email = new.email
                this.name = new.name
                this.password = new.password
                this.createdAt = DateTime.now()
                this.updatedAt = DateTime.now()
            }
            MemberGroup.new {
                this.memberNo = member.id.value
                this.groupName = "${member.name} 의 개인"
                this.createdAt = DateTime.now()
                this.updatedAt = DateTime.now()
            }
            member
        }
}