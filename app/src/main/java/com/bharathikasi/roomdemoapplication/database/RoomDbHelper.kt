package com.bharathikasi.roomdemoapplication.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.bharathikasi.roomdemoapplication.data_access_object.SubscriberDAO
import com.bharathikasi.roomdemoapplication.dataclass.Subscriber

@Database(entities = [Subscriber::class],version = 1)
abstract class RoomDbHelper :RoomDatabase(){
    abstract val mSubscriberDAO:SubscriberDAO

    companion object {
        private val DATABASE_NAME = "RoomDatabase"

        @Volatile
        private var INSTANCE: RoomDbHelper? = null
        fun getInstance(pContext: Context): RoomDbHelper {
            synchronized(this) {
                var lInstance = INSTANCE
                if (lInstance == null) {
                    lInstance = Room.databaseBuilder(
                        pContext.applicationContext,
                        RoomDbHelper::class.java, DATABASE_NAME
                    ).build()
                }
                return lInstance
            }
        }

    }
}