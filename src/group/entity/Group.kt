package com.platform.group.entity

import com.platform.exposed.IntIdTableWithDatetime

object GroupTable : IntIdTableWithDatetime("group") {
    var name  = varchar("name", 200)
    var owner = integer("owner")
}

