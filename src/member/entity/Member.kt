package com.platform.member.entity

import com.platform.exposed.IntIdTableWithDatetime
import org.jetbrains.exposed.dao.*

object MemberTable : IntIdTableWithDatetime("member") {
    var name  = varchar("name", 200)
    var email = varchar("email", 300).uniqueIndex()
    var password =  varchar("password", 150)
}

class Member(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<Member>(MemberTable)
    var name     by MemberTable.name
    var email    by MemberTable.email
    var password by MemberTable.password
}

object MemberGroupTable : IntIdTable("memberGroup") {
    var groupNo = integer("groupNo")
    var memberNo = integer("memberNo")
    init {
        uniqueIndex(groupNo, memberNo)
    }
}

class MemberGroup(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<MemberGroup>(MemberGroupTable)
    var groupNo = MemberGroupTable.integer("groupNo")
    var memberNo = MemberGroupTable.integer("memberNo")
}

