package com.example.insuranceapp.data.storage.pref

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import javax.inject.Inject

class AppPreferences @Inject constructor(
    mContext : Context
):PreferenceHelper{
    private val sharedPreferences: SharedPreferences =
        mContext.getSharedPreferences(mContext.packageName, Context.MODE_PRIVATE)


    override fun setUserPhoneNumber(phoneNo :String) {
        sharedPreferences.edit { putString(USER_PASSWORD,phoneNo).apply() }
    }

    override fun getUserPhoneNumber(): String {
        return sharedPreferences.getString(USER_PASSWORD,"") ?: ""
    }

    companion object{
        private const val USER_PASSWORD = "USER_PASSWORD"
    }

}