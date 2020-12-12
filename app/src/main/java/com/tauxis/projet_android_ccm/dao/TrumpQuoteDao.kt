package com.tauxis.projet_android_ccm.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.tauxis.projet_android_ccm.model.TrumpQuote

@Dao
interface TrumpQuoteDao {
    @Query("SELECT * FROM trump_quote_table ORDER BY id ASC")
    fun selectAll(): LiveData<List<TrumpQuote>>

    @Insert()
    fun insert(trumpQuote: TrumpQuote)

    @Query("DELETE FROM trump_quote_table")
    fun deleteAll()
}