package com.bharathikasi.roomdemoapplication.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bharathikasi.roomdemoapplication.R
import com.bharathikasi.roomdemoapplication.adapter.SubscriberRecyclerViewAdapter
import com.bharathikasi.roomdemoapplication.database.RoomDbHelper
import com.bharathikasi.roomdemoapplication.databinding.ActivityMainBinding
import com.bharathikasi.roomdemoapplication.dataclass.Subscriber
import com.bharathikasi.roomdemoapplication.repository.SubscriberRepository
import com.bharathikasi.roomdemoapplication.viewmodel.SubscriberViewModel
import com.bharathikasi.roomdemoapplication.viewmodel_factory.SubscriberViewModelFactory

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"
    private lateinit var mDataBinding: ActivityMainBinding
    private lateinit var mSubscribeViewModel:SubscriberViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mDataBinding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        val lDAO = RoomDbHelper.getInstance(applicationContext).mSubscriberDAO
        val lRepository = SubscriberRepository(lDAO)
        val lFactory = SubscriberViewModelFactory(lRepository)
        mSubscribeViewModel = ViewModelProvider(this,lFactory).get(SubscriberViewModel::class.java)
        mDataBinding.subscriberViewModel = mSubscribeViewModel
        mDataBinding.lifecycleOwner = this
        //displayAllSubscribers()

        mSubscribeViewModel.mMessage.observe(this, Observer {
            Toast.makeText(this,it.getContentIfNotHandled(),Toast.LENGTH_LONG).show()
        })
    }

    override fun onResume() {
        super.onResume()
        Log.i(TAG,"onresume")
        initSubscriberRecyclerView()
    }

    override fun onPause() {
        super.onPause()
        Log.i(TAG,"onrpause")
    }

    private fun listItemOnclick(pSubscriber : Subscriber){
        Log.i(TAG,"the psubscirber name is "+pSubscriber.pName)
        //Toast.makeText(this,"Selected Subscriber name is ${pSubscriber.pName}",Toast.LENGTH_LONG).show()
        mSubscribeViewModel.initUpdateAndDelete(pSubscriber)
    }
    private fun displayAllSubscribers(){
        mSubscribeViewModel.mAllSubscribers.observe(this, Observer {
            Log.i(TAG,"the all subscribers is ${it.toString()}")
            mDataBinding.SubscriberRV.adapter = SubscriberRecyclerViewAdapter(it) { selectedItem: Subscriber -> listItemOnclick(selectedItem) }
        })
    }

    private fun initSubscriberRecyclerView(){
        mDataBinding.SubscriberRV.layoutManager = LinearLayoutManager(this)
        displayAllSubscribers()
    }


}