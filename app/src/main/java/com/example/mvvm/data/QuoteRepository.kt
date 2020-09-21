package com.example.mvvm.data

class QuoteRepository private constructor(private val quoteDao: FakeDao) {

    fun addQuote(quote: Quote) {
        quoteDao.addQuote(quote)
    }

    fun getQuotes() = quoteDao.getQuotes()

    companion object {
        @Volatile private var instance: QuoteRepository? = null

        fun getInstance(quoteDao: FakeDao) = instance ?: synchronized(this) {
            instance ?: QuoteRepository(quoteDao).also { instance = it }
        }
    }
}