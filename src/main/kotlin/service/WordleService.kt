package service

import wordle.Dictionary
import wordle.Sign
import wordle.Wordle

data class WordleService(private val dictionary: Dictionary, var wordle: Wordle = Wordle(dictionary.pick())) {

    private val guessHistory = mutableListOf<String>()
    private var lastGuess = ""

    fun newGame(size: Int = 0) {
        wordle = Wordle(dictionary.pick(size))
        guessHistory.removeAll { true }
    }

    fun hasWon(): Boolean {
        return lastGuess == wordle.target
    }

    fun isGuessValid(guess: String) = wordle.target.length == guess.length && dictionary.has(guess)

    fun guess(guess: String): List<Sign> {
        val signed = wordle.guess(guess)
        guessHistory.add(toPrettySignedWord(guess.toUpperCase(), signed))
        lastGuess = guess.toLowerCase()
        return signed
    }

    private fun toPrettySignedWord(guess: String, result: List<Sign>): String =
        guess.mapIndexed { idx, c -> toPrettySignedChar(c, result[idx]) }.joinToString("")

    private fun toPrettySignedChar(c: Char, sign: Sign): String {
        return when (sign) {
            Sign.RIGHT -> "[$c]"
            Sign.WRONG_PLACE -> "($c)"
            else -> " $c "
        }
    }

    fun getPrettyHistory(): String {
        return guessHistory
            .joinToString("\n")

    }
}