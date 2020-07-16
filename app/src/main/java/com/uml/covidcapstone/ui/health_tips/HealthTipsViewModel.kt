package com.uml.covidcapstone.ui.health_tips

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HealthTipsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is health tips Fragment"
    }
    val text: LiveData<String> = _text
}