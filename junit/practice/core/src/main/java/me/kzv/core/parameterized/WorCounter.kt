package me.kzv.core.parameterized

class WordCounter {
    fun countWords(sentence: String): Int {
        return sentence.split(" ").size
    }
}
