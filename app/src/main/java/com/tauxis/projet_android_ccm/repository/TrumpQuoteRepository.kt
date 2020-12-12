package com.tauxis.projet_android_ccm.repository

import androidx.lifecycle.LiveData
import com.tauxis.projet_android_ccm.architecture.CustomApplication
import com.tauxis.projet_android_ccm.architecture.RetrofitBuilder
import com.tauxis.projet_android_ccm.model.TrumpQuote
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TrumpQuoteRepository {
    private val mTrumpDao = CustomApplication.instance.mApplicationDatabase.mTrumpDao()

    fun selectAllTrumpQuote() : LiveData<List<TrumpQuote>> {
        return mTrumpDao.selectAll()
    }

    private suspend fun insertTrumpQuote(trumpQuote: TrumpQuote) = withContext(
        Dispatchers.IO) {
        mTrumpDao.insert(trumpQuote)
    }

    suspend fun deleteAllTrumpQuote() = withContext(Dispatchers.IO) {
        mTrumpDao.deleteAll()
    }

    suspend fun fetchData() {
        insertTrumpQuote(RetrofitBuilder.getTrumpQuote().getRandomQuote())
    }
}