package com.platform.database

import com.platform.group.entity.GroupTable
import com.platform.member.entity.MemberGroupTable
import com.platform.member.entity.MemberTable
import com.platform.objectives.entity.ObjectivesTable
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils.create
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction


object DatabaseFactory {

    fun init(isDev : Boolean) {
        Database.connect(hikari(isDev))
        if (isDev){ initDatabase()}
    }

    private fun initDatabase() {
        transaction {
            create(ObjectivesTable)
            create(MemberTable)
            create(GroupTable)
            create(MemberGroupTable)
        }
    }

    suspend fun <T> dbQuery(block: () -> T): T =  withContext(Dispatchers.IO) {
            transaction { block() }
    }

/*
    suspend fun getAllWidgets(): List<Widget> = dbQuery {
        Widgets.selectAll().map { toWidget(it) }
    }
*/


    private fun hikari(dev: Boolean): HikariDataSource {
       if (dev) { return getHikariDataSource() }
       return getHikariDataSource()
    }

    private fun getHikariDataSource() =
        HikariDataSource(HikariConfig().apply {
            this.driverClassName = "org.h2.Driver"
            this.jdbcUrl = "jdbc:h2:mem:;INIT=CREATE SCHEMA IF NOT EXISTS okr;DB_CLOSE_DELAY=-1;DATABASE_TO_UPPER=false"
            this.maximumPoolSize = 3
            this.isAutoCommit = false
            this.transactionIsolation = "TRANSACTION_REPEATABLE_READ"
            this.validate()
        })
}