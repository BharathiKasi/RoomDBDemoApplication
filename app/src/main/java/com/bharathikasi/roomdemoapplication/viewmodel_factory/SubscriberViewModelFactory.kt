package com.bharathikasi.roomdemoapplication.viewmodel_factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bharathikasi.roomdemoapplication.repository.SubscriberRepository
import com.bharathikasi.roomdemoapplication.viewmodel.SubscriberViewModel
import java.lang.IllegalStateException

class SubscriberViewModelFactory(private val pSubscriberRepository: SubscriberRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(SubscriberViewModel::class.java)){
            return SubscriberViewModel(pSubscriberRepository) as T
        }
        throw IllegalStateException("Unknown view model class ")
    }

}