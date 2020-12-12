package com.tauxis.projet_android_ccm.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.tauxis.projet_android_ccm.model.ObjectDataSample


@Dao
interface CustomDao {

    @Query("SELECT * FROM my_custom_pojo_table")
    fun selectAll(): LiveData<List<ObjectDataSample>>

    @Insert()
    fun insert(myData: ObjectDataSample)

    @Query("DELETE FROM my_custom_pojo_table")
    fun deleteAll()

    @Delete
    fun delete(myData: ObjectDataSample)
}