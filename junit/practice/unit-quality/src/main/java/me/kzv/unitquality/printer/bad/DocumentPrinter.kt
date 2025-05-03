package me.kzv.unitquality.printer.bad

class DocumentPrinter (
    val document: Document,
) {
    fun printDocument() {
        when (document.type) {
            DocumentType.WORD_DOCUMENT -> printWORDDocument()
            DocumentType.PDF_DOCUMENT -> printPDFDocument()
            DocumentType.TEXT_DOCUMENT -> printTextDocument()
            DocumentType.BINARY_DOCUMENT -> printBinaryDocument()
        }
    }

    private fun printBinaryDocument() {
        println("binary")
    }

    private fun printTextDocument() {
        println("text")
    }

    private fun printPDFDocument() {
        println("pdf")
    }

    private fun printWORDDocument() {
        println("word")
    }
}