package io

import wordle.Dictionary
import wordle.toStandard
import java.io.File

class DictionaryFileReader(private val filePath: String) : DictionaryFactory {
    override fun createDictionary(): Dictionary {
        val words = File(filePath)
            .readLines()
            .filter { it.all { c -> c.isLetter() } }
            .map { it.toStandard() }
        println("words : ${words.size}")
        return Dictionary(words)
    }
}


