package com.example.mvvm.ui.quotes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.mvvm.R
import com.example.mvvm.data.Quote
import com.example.mvvm.utilities.InjectorUtils
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.StringBuilder

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initializeUI()
    }

    private fun initializeUI() {
        val factory = InjectorUtils.provideQuotesViewModelFactory(application)
        val viewModel = ViewModelProviders.of(this, factory)
            .get(QuotesViewModel::class.java)

        viewModel.getQuotes().observe(this, Observer { quotes ->
            val stringBuilder = StringBuilder()
            quotes.forEach{ quote ->
                stringBuilder.append("$quote\n\n")
            }
            textView_quotes.text = stringBuilder.toString()
        })

        addButton.setOnClickListener {
            val quote = Quote(quote_editText.text.toString(), author_editText.text.toString())
            viewModel.addQuote(quote)
            quote_editText.setText("")
            author_editText.setText("")
        }
    }
}