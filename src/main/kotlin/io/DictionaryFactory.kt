package io

import wordle.Dictionary

interface DictionaryFactory {

    fun createDictionary(): Dictionary
}