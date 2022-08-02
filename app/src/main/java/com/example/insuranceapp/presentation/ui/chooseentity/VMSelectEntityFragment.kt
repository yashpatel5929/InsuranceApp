package com.example.insuranceapp.presentation.ui.chooseentity

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.insuranceapp.data.constants.Constants
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class VMSelectEntityFragment @Inject constructor():ViewModel() {
    val createEntity = MutableLiveData<Boolean>()
    val joinEntity = MutableLiveData<Boolean>()

    fun createEntity(){
        Constants.ENTITYCLASS = 1
        createEntity.postValue(true)
    }
    fun joinEntity(){
        Constants.ENTITYCLASS = 2
        joinEntity.postValue(true)
    }

    fun validateEntity(){

    }
}