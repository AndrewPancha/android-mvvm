package com.example.mvvm.utilities

import android.app.Application
import com.example.mvvm.data.FakeDatabase
import com.example.mvvm.data.QuoteRepository
import com.example.mvvm.ui.quotes.QuotesViewModelFactory

object InjectorUtils {

    fun provideQuotesViewModelFactory(application: Application): QuotesViewModelFactory {
        val quotesRepository = QuoteRepository.getInstance(FakeDatabase.getInstance().quoteDao)
        return QuotesViewModelFactory(quotesRepository, application)
    }
}