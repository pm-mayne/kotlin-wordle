package wordle

data class Wordle(val target: String) {

    fun guess(guess: String): List<Sign> {
        return guess
            .toLowerCase()
            .mapIndexed{ idx, c -> getSign(c, idx)}
    }

    private fun getSign(c: Char, idx: Int): Sign {
        return when {
            c == target[idx] -> {
                Sign.RIGHT
            }
            target.contains(c) -> {
                Sign.WRONG_PLACE
            }
            else -> Sign.WRONG
        }
    }
}

enum class Sign {
    WRONG,
    WRONG_PLACE,
    RIGHT
}
