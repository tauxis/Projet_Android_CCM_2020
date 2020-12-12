package com.tauxis.projet_android_ccm.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDelegate
import com.tauxis.projet_android_ccm.R
import com.tauxis.projet_android_ccm.firebase.view.FirebaseLoginActivity
import com.tauxis.projet_android_ccm.preferences.MyPreferences
import com.tauxis.projet_android_ccm.sensor.view.SensorActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainButtonRecyclerView.setOnClickListener { goToRecyclerView() }
        mainButtonRetrofit.setOnClickListener { goToRetrofit() }
        mainButtonFireBase.setOnClickListener { goToFirebase() }
        mainButtonSensor.setOnClickListener { goToSensorRealTime() }
        imageViewManage.setOnClickListener{chooseThemeDialog()}
    }

    private fun goToRecyclerView() {
        startActivity(Intent(this, RecyclerViewActivity::class.java))
    }
    private fun goToRetrofit() {
        startActivity(Intent(this, RetrofitActivity::class.java))
    }
    private fun goToFirebase() {
        startActivity(Intent(this, FirebaseLoginActivity::class.java))
    }
    private fun goToSensorRealTime() {
        startActivity(Intent(this, SensorActivity::class.java))
    }


    private fun chooseThemeDialog() {

        val builder = AlertDialog.Builder(this)
        builder.setTitle("Choisir le thème :")
        val styles = arrayOf("Jour","Nuit")
        val checkedItem = MyPreferences(this).darkMode

        builder.setSingleChoiceItems(styles, checkedItem) { dialog, which ->
            when (which) {
                0 -> {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                    MyPreferences(this).darkMode = 0
                    delegate.applyDayNight()
                    dialog.dismiss()
                }
                1 -> {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                    MyPreferences(this).darkMode = 1
                    delegate.applyDayNight()
                    dialog.dismiss()
                }
            }
        }
        val dialog = builder.create()
        dialog.show()
    }
}