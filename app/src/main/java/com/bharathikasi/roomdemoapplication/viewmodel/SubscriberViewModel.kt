package com.bharathikasi.roomdemoapplication.viewmodel

import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bharathikasi.roomdemoapplication.dataclass.Subscriber
import com.bharathikasi.roomdemoapplication.event.Event
import com.bharathikasi.roomdemoapplication.repository.SubscriberRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class SubscriberViewModel(private val pSubscriberRepository: SubscriberRepository) : ViewModel() ,Observable{

    @Bindable
    val mSubscriberNameLiveData =  MutableLiveData<String>()
    @Bindable
    val mSubscriberEmailLiveData = MutableLiveData<String>()
    @Bindable
    val mSaveOrUpdateButtonLiveData = MutableLiveData<String>()
    @Bindable
    val mClearAllOrDeleteButtonLiveData = MutableLiveData<String>()

    private val mStatusMessage = MutableLiveData<Event<String>>()
    val mMessage:LiveData<Event<String>>  get() = mStatusMessage

    val mAllSubscribers = pSubscriberRepository.lAllSubscribersLiveData

    private var mIsUpdateOrDelete = false;
    private var mSubscriberUpdateOrDelete:Subscriber? = null

    private val mCoroutineScope = CoroutineScope(Dispatchers.IO)

    init {
        mSaveOrUpdateButtonLiveData.value = "Save"
        mClearAllOrDeleteButtonLiveData.value = "Clear All"
    }

    fun saveOrUpdateSubscriber(){
        val lSubscriberName = mSubscriberNameLiveData.value!!
        val lSubscriberEmail = mSubscriberEmailLiveData.value!!
        if(mIsUpdateOrDelete && mSubscriberUpdateOrDelete!=null){
            mSubscriberUpdateOrDelete!!.pName = lSubscriberName
            mSubscriberUpdateOrDelete!!.pEmail = lSubscriberEmail
            updateExistingSubscriber(mSubscriberUpdateOrDelete!!)
        }else{
            insertNewSubscriber(Subscriber(0,lSubscriberName,lSubscriberEmail))
            mSubscriberEmailLiveData.value = null
            mSubscriberNameLiveData.value = null
        }
    }

    fun clearAllOrDeleteSubscriber(){
        if(mIsUpdateOrDelete && mSubscriberUpdateOrDelete!=null){
            deleteExistingSubscriber(mSubscriberUpdateOrDelete!!)
        }else{
            deleteAllSubscribers()
        }
    }

    fun insertNewSubscriber(pSubscriber: Subscriber){
        mCoroutineScope.launch {
            pSubscriberRepository.insertNewSubscirber(pSubscriber)
            mStatusMessage.value = Event("New Subscriber added successfully....")

        }
    }

    fun updateExistingSubscriber(pSubscriber: Subscriber){
        mCoroutineScope.launch {
            pSubscriberRepository.updateExistSubscriber(pSubscriber)

        }
        mStatusMessage.value = Event("updated Subscriber successfully....")

        mSubscriberNameLiveData.value = null
        mSubscriberEmailLiveData.value = null
        mSubscriberUpdateOrDelete = null
        mIsUpdateOrDelete = false
        mSaveOrUpdateButtonLiveData.value = "Save"
        mClearAllOrDeleteButtonLiveData.value = "Clear All"
    }

    fun deleteExistingSubscriber(pSubscriber: Subscriber){
        mCoroutineScope.launch {
            pSubscriberRepository.deleteExistSubscriber(pSubscriber)
        }
        mStatusMessage.value = Event("deleted Subscriber successfully....")
        mSubscriberNameLiveData.value = null
        mSubscriberEmailLiveData.value = null
        mIsUpdateOrDelete = false
        mSubscriberUpdateOrDelete = null
        mSaveOrUpdateButtonLiveData.value = "Save"
        mClearAllOrDeleteButtonLiveData.value = "Clear All"
    }


    fun deleteAllSubscribers(){
        mCoroutineScope.launch {
            pSubscriberRepository.deleteAllSubscriber()
        }
        mStatusMessage.value = Event("deleted All Subscriber's successfully....")

    }

    fun initUpdateAndDelete(pSubscriber: Subscriber){
        mSubscriberNameLiveData.value = pSubscriber.pName
        mSubscriberEmailLiveData.value = pSubscriber.pEmail
        mIsUpdateOrDelete = true
        mSubscriberUpdateOrDelete = pSubscriber
        mClearAllOrDeleteButtonLiveData.value = "Delete"
        mSaveOrUpdateButtonLiveData.value = "Update"
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    }

}