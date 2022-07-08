package wordle

interface DictionaryFactory {

    fun createDictionary(): Dictionary
}