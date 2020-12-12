package com.tauxis.projet_android_ccm.sensor.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import com.tauxis.projet_android_ccm.R
import com.tauxis.projet_android_ccm.sensor.model.AccelerometerValue
import com.tauxis.projet_android_ccm.sensor.viewmodel.SensorViewModel
import kotlinx.android.synthetic.main.activity_sensor.*

class SensorActivity : AppCompatActivity() {

    private var SensorRef= FirebaseDatabase.getInstance().getReference("Sensor")
    private var myRef= FirebaseDatabase.getInstance().getReference("Text")
    private val database = Firebase.database
    private val textRef = database.getReference("Text")

    private lateinit var mViewModel: SensorViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sensor)
        mViewModel = ViewModelProvider(this)[SensorViewModel::class.java]

        SensorRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                sensor_x.text = getString(R.string.accelerometer_value_x)+ String.format("%.3f", dataSnapshot.child("AxeX").value)
                sensor_y.text = getString(R.string.accelerometer_value_y)+ String.format("%.3f", dataSnapshot.child("AxeY").value)
                sensor_z.text = getString(R.string.accelerometer_value_z)+ String.format("%.3f", dataSnapshot.child("AxeZ").value)
            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w("TAG", "Failed to read value.", error.toException())
            }
        })

        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                val value = dataSnapshot.getValue<String>()
                editTextRealTime.setText(value, TextView.BufferType.EDITABLE)
                editTextRealTime.setSelection(editTextRealTime.getText().length)
            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w("TAG", "Failed to read value.", error.toException())
            }
        })

        editTextRealTime.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int,before: Int, count: Int) {
                sendData()
            }
        })
    }

    fun sendData(){
        textRef.setValue(editTextRealTime.text.toString())
    }

}
