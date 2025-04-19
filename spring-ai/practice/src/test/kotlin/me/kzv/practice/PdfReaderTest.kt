package me.kzv.practice

import me.kzv.practice.pdf.DefaultPdfReader
import me.kzv.practice.pdf.DefaultTextParser
import org.springframework.core.io.ClassPathResource

class PdfReaderTest {

}

fun main () {
    val data = DefaultPdfReader().read(ClassPathResource("data.pdf").file)
    for (text in DefaultTextParser().parse(data)) {
        println(text)
    }
}