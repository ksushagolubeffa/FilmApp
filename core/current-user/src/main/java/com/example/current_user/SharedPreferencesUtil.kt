package com.example.current_user

import android.content.Context

class SharedPreferencesUtil(context: Context) {
    private val sharedPreferences =
        context.getSharedPreferences("app_shared_prefs", Context.MODE_PRIVATE)

    fun getValue(key: String, defaultValue: Int): Int? {
        return sharedPreferences.getInt(key, defaultValue)
    }

    fun setValue(key: String, value: Int) {
        sharedPreferences.edit().putInt(key, value).apply()
    }

    fun clearValue(key: String) {
        sharedPreferences.edit().remove(key).apply()
    }
}