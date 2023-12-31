package com.github.hirotask.infra

import java.sql.Connection
import java.sql.DriverManager
import java.sql.ResultSet
import java.sql.SQLException

/**
 * データベース接続のためのクラス
 *
 */
class Database {
    companion object {
        private const val DATABASE_DRIVER = "org.mariadb.jdbc.Driver"
        private const val HOSTNAME = "mc_db"
        private const val PORT = "3306"
        private const val DBNAME = "zonkills"
        private const val DATABASE_URL = "jdbc:mariadb://$HOSTNAME:$PORT/$DBNAME"
        private const val USERNAME = "root"
        private const val PASSWORD = "root"
    }

    private var connection: Connection? = null

    /**
     * データベースに接続する
     *
     * @return
     */
    fun connect(): Connection? {
        try {
            Class.forName(DATABASE_DRIVER)
            connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD) as Connection
        } catch (e: ClassNotFoundException) {
            e.printStackTrace()
        } catch (e: SQLException) {
            e.printStackTrace()
        }
        return this.connection
    }

    /**
     * データベースから切断する
     *
     * @return
     */
    fun disconnect(): Boolean {
        try {
            connection?.close()
            connection = null
            return true
        } catch (e: SQLException) {
            e.printStackTrace()
        }
        return false
    }

    /**
     * SELECT文を実行する
     *
     * @param query 実行するクエリ
     * @return 実行結果
     */
    fun select(query: String): ResultSet? {
        val statement = connection?.createStatement()

        return statement?.executeQuery(query)
    }

    /**
     * UPDATE, DELETE, INSERTを実行する
     *
     * @param query 実行するクエリ
     * @return 実行後変更されたインデックス
     */
    fun createOrUpdateOrDelete(query: String): Int {
        val statement = connection?.createStatement()

        return statement?.executeUpdate(query) ?: return -1
    }
}
