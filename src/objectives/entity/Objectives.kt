package com.platform.objectives.entity

import com.platform.exposed.IntIdTableWithDatetime

object ObjectivesTable : IntIdTableWithDatetime("objectives") {
    var title = varchar("title",200)
    var groupNo = integer("groupNo")
}


