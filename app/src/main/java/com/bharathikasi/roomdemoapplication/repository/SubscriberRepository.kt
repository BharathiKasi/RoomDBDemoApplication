package com.bharathikasi.roomdemoapplication.repository

import com.bharathikasi.roomdemoapplication.data_access_object.SubscriberDAO
import com.bharathikasi.roomdemoapplication.dataclass.Subscriber

class SubscriberRepository(val pSubscriberDAO: SubscriberDAO) {

    val lAllSubscribersLiveData = pSubscriberDAO.getAllSubscribers()

    suspend fun insertNewSubscirber(pSubscriber: Subscriber){
        pSubscriberDAO.insertNewSubscriber(pSubscriber)
    }

    suspend fun updateExistSubscriber(pSubscriber: Subscriber){
        pSubscriberDAO.updateExistingSubscriber(pSubscriber)
    }

    suspend fun deleteExistSubscriber(pSubscriber: Subscriber){
        pSubscriberDAO.deleteExistingSubscriber(pSubscriber)
    }

    suspend fun deleteAllSubscriber(){
        pSubscriberDAO.deleteAllSubscriber();
    }

}