import org.junit.Test
import service.WordleService
import wordle.Dictionary
import wordle.Sign.*
import wordle.Wordle
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class WordleServiceTest {

    @Test
    fun testGuess() {
        val dictionary = Dictionary(listOf("toto", "tata", "titi", "hello", "lol", "abba", "atoe"))
        val wordleService = WordleService(dictionary)
        wordleService.newGame()
        wordleService.game = Wordle("toto")
        assertTrue(wordleService.isGuessValid("tata"))
        assertFalse(wordleService.isGuessValid("tutu"), "Not in dictionary")
        assertFalse(wordleService.isGuessValid("hello"), "Too long")
        assertFalse(wordleService.isGuessValid("lol"), "Too short")
        val guessTata = wordleService.guess("tata")
        assertFalse(wordleService.hasWon())
        assertEquals(listOf(RIGHT, WRONG, RIGHT, WRONG), guessTata)
        val guessAbba = wordleService.guess("abba")
        assertFalse(wordleService.hasWon())
        assertEquals(listOf(WRONG, WRONG, WRONG, WRONG), guessAbba)
        val guessAtoe = wordleService.guess("atoe")
        assertFalse(wordleService.hasWon())
        assertEquals(listOf(WRONG, WRONG_PLACE, WRONG_PLACE, WRONG), guessAtoe)
        wordleService.guess("toto")
        assertTrue(wordleService.hasWon())
    }
}