package me.kzv.tenancymanager.database

enum class DatabaseType(
    val str: String
) {
    MYSQL("mysql"),
    POSTGRESQL("postgresql"),
    ORACLE("oracle"),
    ;
}
