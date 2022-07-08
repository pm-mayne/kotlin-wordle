package service

import wordle.Dictionary
import wordle.Sign
import wordle.Wordle

data class WordleService(val dictionary: Dictionary, var game: Wordle = Wordle(dictionary.pick())) {

    private val guessHistory = mutableListOf<String>()
    private var lastGuess = ""

    fun newGame(size: Int = 0) {
        game = Wordle(dictionary.pick(size))
        guessHistory.removeAll { true }
    }

    fun hasWon(): Boolean {
        return lastGuess == game.target
    }

    fun isGuessValid(guess: String) = game.target.length == guess.length && dictionary.has(guess)

    fun guess(guess: String) {
        var signed = game.guess(guess)
        guessHistory.add(prettySign(guess.toUpperCase(), signed))
        lastGuess = guess.toLowerCase()
    }

    private fun prettySign(guess: String, result: List<Sign>): String =
        guess.mapIndexed { idx, c -> pretty(c, result[idx]) }.joinToString("")

    private fun pretty(c: Char, sign: Sign): String {
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