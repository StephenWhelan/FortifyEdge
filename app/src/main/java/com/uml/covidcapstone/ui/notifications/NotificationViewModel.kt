package com.example.covidcapstone.ui.notifications

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class NotificationViewModel {
    private val _text = MutableLiveData<String>().apply {
        value = "This is Notification Fragment"
    }
    val text: LiveData<String> = _text
}