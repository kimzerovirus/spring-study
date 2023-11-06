package me.kzv.searchingserver.document

import org.springframework.data.annotation.Id
import org.springframework.data.elasticsearch.annotations.Document

@Document(indexName = "subway")
class SubwayDocument(
    @Id
    val id: String?,
    val station: String,
    val line: String,
    val excode: String
) {

}