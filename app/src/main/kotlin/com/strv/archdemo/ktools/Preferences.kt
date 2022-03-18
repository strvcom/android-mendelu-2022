package com.strv.archdemo.ktools

import android.content.SharedPreferences

private const val PREFS_COUNTER = "prefs_counter"

class Preferences(val prefs: SharedPreferences) {
    fun getCounterValueFromPreferences(): Int {
        return prefs.getInt(PREFS_COUNTER, 0)
    }

    fun updateCounterValueInPreferences(newValue: Int) {
        prefs.edit().putInt(PREFS_COUNTER, newValue).apply()
    }
}
