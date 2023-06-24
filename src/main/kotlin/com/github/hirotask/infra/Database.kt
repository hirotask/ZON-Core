package com.github.hirotask.infra

import java.sql.Connection
import java.sql.DriverManager
import java.sql.ResultSet
import java.sql.SQLException
import java.util.Properties

class Database {
    companion object {
        private const val DATABASE_DRIVER = "org.mariadb.jdbc.Driver"
        private const val HOSTNAME = ""
        private const val PORT = ""
        private const val DBNAME = ""
        private const val DATABASE_URL = "jdbc:mariadb://$HOSTNAME:$PORT/$DBNAME"
        private const val USERNAME = "root"
        private const val PASSWORD = "root"
        private const val MAX_POOL = "250"
    }

    var connection : Connection? = null

    val properties = Properties().let {
        it.setProperty("user", USERNAME)
        it.setProperty("password", PASSWORD)
        it.setProperty("MaxPooledStatements", MAX_POOL)
    }

    fun connect(): Connection? {
        try {
            Class.forName(DATABASE_DRIVER)
            connection = DriverManager.getConnection(DATABASE_URL, properties as Properties?) as Connection
        } catch(e: ClassNotFoundException) {
            e.printStackTrace()
        } catch (e: SQLException) {
            e.printStackTrace()
        }
        return this.connection
    }

    fun disconnect() : Boolean {
        try {
            connection?.close()
            connection = null
            return true
        } catch (e: SQLException) {
            e.printStackTrace()
        }
        return false
    }

    fun select(query: String): ResultSet? {
        val statement = connection?.createStatement()

        return statement?.executeQuery(query)
    }

    fun createOrUpdateOrDelete(query: String): Int {
        val statement = connection?.createStatement()

        return statement?.executeUpdate(query) ?: return -1
    }
}