package me.kzv.common.enums

enum class DatabaseType(
    val dbName: String,
    val driverClassName: String,
) {
    MYSQL("mysql", "com.mysql.cj.jdbc.Driver"),
    POSTGRESQL("postgresql","org.postgresql.Driver"),
    ORACLE("oracle","oracle.jdbc.driver.OracleDriver"),
    ;
}
