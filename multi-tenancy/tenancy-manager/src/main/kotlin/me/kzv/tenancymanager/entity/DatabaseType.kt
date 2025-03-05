package me.kzv.tenancymanager.entity

enum class DatabaseType(
    val str: String
) {
    MYSQL("mysql"),
    POSTGRESQL("postgresql"),
    ORACLE("oracle"),
    ;
}
