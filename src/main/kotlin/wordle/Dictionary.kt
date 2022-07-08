package wordle

data class Dictionary(val words: List<String>) {

    fun pick(size: Int = 0) = words.filter { size == 0 || it.length == size }.random()
    fun has(guess: String) = words.contains(guess.toStandard())

}

fun String.toStandard() : String{
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