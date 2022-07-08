package wordle

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

private fun String.toStandard() : String{
    return this.toLowerCase()
        .replace('é','e',true)
        .replace('è','e',true)
        .replace('ë','e',true)
        .replace('ê','e',true)
        .replace('ï','i',true)
        .replace('î','i',true)
        .replace('ô','o',true)
        .replace('ù','u',true)
        .replace('ü','u',true)
        .replace('ç','c',true)
}
