package me.kzv.searchingserver.document

import com.opencsv.bean.CsvBindByPosition

class Subway(
    @field:CsvBindByPosition(position = 0)
    val code: String,

    @field:CsvBindByPosition(position = 1)
    val station: String,

    @field:CsvBindByPosition(position = 2)
    val line: String,

    @field:CsvBindByPosition(position = 3)
    val excode: String
)
