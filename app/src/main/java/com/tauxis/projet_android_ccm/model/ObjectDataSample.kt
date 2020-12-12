package com.tauxis.projet_android_ccm.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "my_custom_pojo_table")
data class ObjectDataSample (
    @PrimaryKey(autoGenerate = true)
    var id: Long? = null,

    var url : String,
    var value : String,
    var date : String
)