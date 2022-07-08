package app

import service.WordleService
import wordle.DictionaryFileReader

fun main(args: Array<String>) {
    println("Launching Kotlin Wordle")
    val dictionary = DictionaryFileReader(args[0]).createDictionary()
    val wordleService = WordleService(dictionary)
    val size = 7
    wordleService.newGame(size)
    println("[ ][ ][ ][ ][ ][ ][ ]")
    while (!wordleService.hasWon()) {
        println("Your guess: ")
        var guess = readLine()
        while (guess == null || !wordleService.isGuessValid(guess)) {
            println("Please write a $size-letter word that is in the dictionary.")
            guess = readLine()
        }
        wordleService.guess(guess)
        val grid = wordleService.getPrettyHistory()
        println(grid)
    }
    println("Congratulations! The word was indeed ${wordleService.game.target}.")
}


