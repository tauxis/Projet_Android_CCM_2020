package com.tauxis.projet_android_ccm.preferences

import android.content.Context
import android.preference.PreferenceManager

class MyPreferences(context: Context?) {

    companion object {
        private const val DARK_STATUS = "io.tauxis.projet_android_ccm.pref.DARK_STATUS"
    }

    private val preferences = PreferenceManager.getDefaultSharedPreferences(context)

    var darkMode = preferences.getInt(DARK_STATUS, 0)
        set(value) = preferences.edit().putInt(DARK_STATUS, value).apply()

}