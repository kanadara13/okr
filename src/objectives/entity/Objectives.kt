package com.platform.objectives.entity

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntIdTable

object ObjectivesTable : IntIdTable("objectives") {
    var title = varchar("title",200)
}