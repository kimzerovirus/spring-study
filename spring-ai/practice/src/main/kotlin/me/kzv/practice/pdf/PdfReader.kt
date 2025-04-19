package me.kzv.practice.pdf

import org.apache.pdfbox.Loader
import org.apache.pdfbox.text.PDFTextStripper
import java.io.File


interface PdfReader {
    fun read(file: File): String
}

class DefaultPdfReader : PdfReader {
    override fun read(file: File): String {
        try {
            Loader.loadPDF(file).use { document ->
                val stripper = PDFTextStripper()
                val text = stripper.getText(document)
//                println("페이지 수: " + document.numberOfPages)
//                println("PDF 내용:\n$text")

                return text
            }
        } catch (e: Exception) {
            throw e
        }
    }
}