package com.example.insuranceapp.data.storage.pref

interface PreferenceHelper {

    fun setUserPhoneNumber(phoneNumber : String)

    fun getUserPhoneNumber() : String
}