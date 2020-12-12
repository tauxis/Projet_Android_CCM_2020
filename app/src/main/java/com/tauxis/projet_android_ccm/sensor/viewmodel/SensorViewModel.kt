package com.tauxis.projet_android_ccm.sensor.viewmodel

import androidx.lifecycle.ViewModel
import com.tauxis.projet_android_ccm.sensor.repository.SensorRepository

class SensorViewModel : ViewModel() {


    private val mSensorRepository by lazy { SensorRepository() }
    val mAccelerometerMutableLiveData = mSensorRepository.mAccelerometerMutableLiveData
}
