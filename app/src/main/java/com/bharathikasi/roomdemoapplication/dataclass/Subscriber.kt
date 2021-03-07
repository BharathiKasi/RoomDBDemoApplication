package com.bharathikasi.roomdemoapplication.dataclass

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//RoomDatabase will create a table for each entity class...


@Entity(tableName = "Subscriber_database_table")
data class Subscriber(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "Subscriber_id")
    val pId:Int ,

    @ColumnInfo(name = "Subscriber_name")
    var pName:String ,

    @ColumnInfo(name = "Subscriber_email")
    var pEmail:String )