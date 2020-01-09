package com.platform.objectives.entity

import com.platform.exposed.IntIdTableWithDatetime
import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.joda.time.format.DateTimeFormat

object ObjectivesTable : IntIdTableWithDatetime("objectives") {
    var title = varchar("title",200)
    var groupNo = integer("groupNo")
}


class Objectives(id: EntityID<Int>) : IntEntity(id) {
    fun toView() = ObjectivesView(id.value, groupNo, title
        , createdAt.toString(DateTimeFormat.forPattern("yyyy-MM-dd hh:mm:ss"))
        , updatedAt.toString(DateTimeFormat.forPattern("yyyy-MM-dd hh:mm:ss")
    ))

    companion object : IntEntityClass<Objectives>(ObjectivesTable)
    var title       by ObjectivesTable.title
    var groupNo        by ObjectivesTable.groupNo
    var createdAt by ObjectivesTable.createdAt
    var updatedAt by ObjectivesTable.updatedAt
}

class ObjectivesView(id: Int, groupNo: Int, title: String, createdAt: String, updatedAt: String)

