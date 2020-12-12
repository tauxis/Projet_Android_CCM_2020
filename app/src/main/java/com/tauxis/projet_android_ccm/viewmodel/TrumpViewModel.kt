package com.tauxis.projet_android_ccm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tauxis.projet_android_ccm.model.TrumpQuote
import com.tauxis.projet_android_ccm.repository.TrumpQuoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TrumpViewModel : ViewModel(){

    private val mTrumpQuoteRepository: TrumpQuoteRepository by lazy { TrumpQuoteRepository() }
    var mTrumpQuoteLiveData: LiveData<List<TrumpQuote>> = mTrumpQuoteRepository.selectAllTrumpQuote()

    fun fetchNewQuote() {
        viewModelScope.launch(Dispatchers.IO) {
            mTrumpQuoteRepository.fetchData()
        }
    }

    fun deleteAllQuote() {
        viewModelScope.launch(Dispatchers.IO) {
            mTrumpQuoteRepository.deleteAllTrumpQuote()
        }
    }
}