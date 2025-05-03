package me.kzv.unitquality.printer.good

abstract class Document {
    abstract fun printDocument()
}

class PDFDocument : Document() {
    override fun printDocument() {
        printPDFDocument()
    }

    private fun printPDFDocument() {
    }
}

class TextDocument : Document() {
    override fun printDocument() {
        printTextDocument()
    }

    private fun printTextDocument() {
    }
}

class WordDocument : Document() {
    override fun printDocument() {
        printWORDDocument()
    }

    private fun printWORDDocument() {
    }
}

class BinaryDocument : Document() {
    override fun printDocument() {
        printPDFDocument()
    }

    private fun printPDFDocument() {
    }
}