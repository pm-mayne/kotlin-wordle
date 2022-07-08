package wordle

data class Dictionary(val words: List<String>) {

    fun pick(size: Int = 0) = words.filter { size == 0 || it.length == size }.random()
    fun has(guess: String) = words.contains(guess.toLowerCase())

}
