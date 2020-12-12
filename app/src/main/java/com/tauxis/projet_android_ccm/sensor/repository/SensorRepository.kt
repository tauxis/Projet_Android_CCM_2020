package com.tauxis.projet_android_ccm.sensor.repository

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.tauxis.projet_android_ccm.R
import com.tauxis.projet_android_ccm.architecture.CustomApplication
import com.tauxis.projet_android_ccm.sensor.model.AccelerometerValue

class SensorRepository : SensorEventListener {


    private val mSensorManager by lazy {
        CustomApplication.instance.applicationContext.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    }
    private lateinit var mAccelerometerSensor: Sensor
    private lateinit var mAccelerometerValue: AccelerometerValue
    val mAccelerometerMutableLiveData = MutableLiveData<AccelerometerValue>()
    private val database = Firebase.database
    private val xRef = database.getReference("Sensor/AxeX")
    private val yRef = database.getReference("Sensor/AxeY")
    private val zRef = database.getReference("Sensor/AxeZ")

    init {
        if (mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) != null) {
            mAccelerometerSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
            mSensorManager.registerListener(
                this,
                mAccelerometerSensor, SensorManager.SENSOR_DELAY_NORMAL
            )
        }
    }

    override fun onSensorChanged(sensorEvent: SensorEvent?) {
        if (sensorEvent?.sensor?.type == Sensor.TYPE_ACCELEROMETER) {
            mAccelerometerValue = AccelerometerValue(
                sensorEvent.values[0],
                sensorEvent.values[1],
                sensorEvent.values[2]
            )
            mAccelerometerMutableLiveData.postValue(mAccelerometerValue)

            xRef.setValue(mAccelerometerValue.axeX)
            yRef.setValue(mAccelerometerValue.axeY)
            zRef.setValue(mAccelerometerValue.axeZ)

        }
    }



    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        // Do what you want when accuracy changed for a given sensor
    }
}

