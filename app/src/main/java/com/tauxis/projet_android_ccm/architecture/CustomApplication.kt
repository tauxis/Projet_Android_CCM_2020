package com.tauxis.projet_android_ccm.architecture

import android.app.Application
import androidx.room.Room

class CustomApplication : Application() {

    ////////////////////////////////////////////
    // Attribute ///////////////////////////////
    ////////////////////////////////////////////

    companion object {
        lateinit var instance: CustomApplication
    }

    val mApplicationDatabase: CustomRoomDatabase by lazy {
        Room.databaseBuilder(
            applicationContext,
            CustomRoomDatabase::class.java,
            "AlexisTaupinDatabase"
        ).fallbackToDestructiveMigration().build()
    }

    ////////////////////////////////////////////
    // Managing Lifecycle //////////////////////
    ////////////////////////////////////////////

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}