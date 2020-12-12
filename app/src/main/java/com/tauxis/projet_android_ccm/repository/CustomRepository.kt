package com.tauxis.projet_android_ccm.repository

import androidx.lifecycle.LiveData
import com.tauxis.projet_android_ccm.architecture.CustomApplication
import com.tauxis.projet_android_ccm.model.ObjectDataSample
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CustomRepository {

    ////////////////////////////////////////////
    // Attribute ///////////////////////////////
    ////////////////////////////////////////////

    // We get the instance of dao
    private val dao = CustomApplication.instance.mApplicationDatabase.mCustomDao()

    ////////////////////////////////////////////
    // Public methods //////////////////////////
    ////////////////////////////////////////////

    fun getDataFromDatabase(): LiveData<List<ObjectDataSample>> {
        return dao.selectAll()
    }

    suspend fun insert(myData: ObjectDataSample) = withContext(Dispatchers.IO) {
        dao.insert(myData)
    }
    suspend fun delete() = withContext(Dispatchers.IO) {
        dao.deleteAll()
    }

    suspend fun deleteItem(myData: ObjectDataSample) = withContext(Dispatchers.IO) {
        dao.delete(myData)
    }
}
