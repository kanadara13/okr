package com.platform.database

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.jetbrains.exposed.sql.Database


object DatabaseFactory {

    fun init(isDev : Boolean) {
        Database.connect(hikari(isDev))
    }


    private fun hikari(dev: Boolean): HikariDataSource {
       if (dev) { return getHikariDataSource() }
       return getHikariDataSource()
    }

    private fun getHikariDataSource() =
        HikariDataSource(HikariConfig().apply {
            this.driverClassName = "org.h2.Driver"
            this.jdbcUrl = "jdbc:h2:mem:test"
            this.maximumPoolSize = 3
            this.isAutoCommit = false
            this.transactionIsolation = "TRANSACTION_REPEATABLE_READ"
            this.validate()
        })
}