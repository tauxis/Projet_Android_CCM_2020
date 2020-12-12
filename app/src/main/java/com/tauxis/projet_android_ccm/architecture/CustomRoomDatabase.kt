package com.tauxis.projet_android_ccm.architecture

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.tauxis.projet_android_ccm.dao.CustomDao
import com.tauxis.projet_android_ccm.dao.TrumpQuoteDao
import com.tauxis.projet_android_ccm.model.ObjectDataSample
import com.tauxis.projet_android_ccm.model.TrumpQuote

@TypeConverters(Converters::class)
@Database(
    entities = [
        TrumpQuote::class,
        ObjectDataSample::class
    ],
    version = 2,
    exportSchema = false
)
abstract class CustomRoomDatabase : RoomDatabase() {
    abstract fun mTrumpDao(): TrumpQuoteDao
    abstract fun mCustomDao(): CustomDao
}