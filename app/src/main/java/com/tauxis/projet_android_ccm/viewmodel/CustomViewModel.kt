package com.tauxis.projet_android_ccm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tauxis.projet_android_ccm.model.ObjectDataSample
import com.tauxis.projet_android_ccm.repository.CustomRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*


class CustomViewModel : ViewModel() {

    ////////////////////////////////////////////
    // Attribute ///////////////////////////////
    ////////////////////////////////////////////

    private val repository: CustomRepository by lazy { CustomRepository() }

    // Create a mutable live data which will contains out data
    var myDataMutableLiveData: LiveData<List<ObjectDataSample>> = repository.getDataFromDatabase()

    fun insertData(url: String, value: String) {
        val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
        val currentDate = sdf.format(Date())
        viewModelScope.launch(Dispatchers.IO) {
            repository.insert(ObjectDataSample(null, url, value, currentDate))
        }
    }

    fun deleteData(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.delete()
        }
    }
    fun deleteItem(item : ObjectDataSample){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteItem(item)
        }
    }

}