package com.bharathikasi.roomdemoapplication.data_access_object

import androidx.lifecycle.LiveData
import androidx.room.*
import com.bharathikasi.roomdemoapplication.dataclass.Subscriber

@Dao
interface SubscriberDAO {

    @Insert
    suspend fun insertNewSubscriber(pSubscriber: Subscriber)

    @Update
    suspend fun updateExistingSubscriber(pSubscriber: Subscriber)

    @Delete
    suspend fun deleteExistingSubscriber(pSubscriber: Subscriber)

    @Query("DELETE FROM Subscriber_database_table")
    suspend fun deleteAllSubscriber()

    @Query("SELECT * FROM Subscriber_database_table")
    fun getAllSubscribers() : LiveData<List<Subscriber>>
}