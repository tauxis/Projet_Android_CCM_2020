package com.tauxis.projet_android_ccm.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.*


@Entity(tableName = "trump_quote_table")
data class TrumpQuote(
    @Expose
    @SerializedName("value")
    val quote: String,
    @Expose
    @SerializedName("appeared_at")
    val date: Date
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}