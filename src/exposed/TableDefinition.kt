package com.platform.exposed

import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.dao.IdTable
import org.jetbrains.exposed.sql.Column

open class IntIdTableWithDatetime(name: String = "", columnName: String = "id") : IdTable<Int>(name) {
    override val id: Column<EntityID<Int>> = integer(columnName).autoIncrement().primaryKey().entityId()
    var createdAt = datetime("createdAt")
    var updatedAt = datetime("updatedAt")
}
