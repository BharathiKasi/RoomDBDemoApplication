package com.bharathikasi.roomdemoapplication.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bharathikasi.roomdemoapplication.R
import com.bharathikasi.roomdemoapplication.databinding.SubscriberRecyclerViewBinding
import com.bharathikasi.roomdemoapplication.dataclass.Subscriber

class SubscriberRecyclerViewAdapter(private val pSubscriberList:List<Subscriber> , private val pItemClickListener :(Subscriber)-> Unit) : RecyclerView.Adapter<SubscriberRecyclerViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SubscriberRecyclerViewHolder {
      val lLayoutInflater = LayoutInflater.from(parent.context)
        val lBinding:SubscriberRecyclerViewBinding = DataBindingUtil.inflate<SubscriberRecyclerViewBinding>(lLayoutInflater,R.layout.subscriber_recycler_view,parent,false)
        return SubscriberRecyclerViewHolder(lBinding)
    }

    override fun onBindViewHolder(pHolder: SubscriberRecyclerViewHolder, pPosition: Int) {
        pHolder.bind(pSubscriberList[pPosition], pItemClickListener)
    }

    override fun getItemCount(): Int {
        return pSubscriberList.size
    }


}
class SubscriberRecyclerViewHolder(private val pBinding:SubscriberRecyclerViewBinding) : RecyclerView.ViewHolder(pBinding.root){

    fun bind(pSubscriber:Subscriber,pItemClickListener: (Subscriber) -> Unit){
        pBinding.subscriberNameTV.text = pSubscriber.pName
        pBinding.subscriberEmailTV.text = pSubscriber.pEmail
        /*pBinding.subscriberListItem.setOnClickListener {
            Log.i("adapter ","adapter onclick")
            //pItemClickListener(pSubscriber)
        }*/
        pBinding.cardview.setOnClickListener {
            Log.i("adapter ","adapter onclick")
            pItemClickListener(pSubscriber)
        }

    }
}
