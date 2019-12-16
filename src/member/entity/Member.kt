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
    var createdAt by MemberTable.createdAt
    var updatedAt by MemberTable.updatedAt
}

class MemberView {

}

object MemberGroupTable : IntIdTableWithDatetime("memberGroup") {
    var memberNo = integer("memberNo")
    var groupName = varchar("name", 200)
}

class MemberGroup(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<MemberGroup>(MemberGroupTable)
    var memberNo  by MemberGroupTable.memberNo
    var groupName by MemberGroupTable.groupName
    var createdAt by MemberGroupTable.createdAt
    var updatedAt by MemberGroupTable.updatedAt
}

