package com.brewble.pocketcpmplus

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.brewble.pocketcpmplus.databinding.ListItemLayoutBinding
import com.brewble.pocketcpmplus.model.ListItem


class ListAdapter(listener: Listener): RecyclerView.Adapter<ListItemViewHolder>() {

    private var itemList: List<ListItem>
    private var eventHandler: EventHandler

    init {
        eventHandler = EventHandler(listener)
        itemList = ArrayList()
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ListItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent!!.context)
        val itemView: ListItemLayoutBinding = DataBindingUtil.inflate(layoutInflater, R.layout.list_item_layout, parent,false)
        return ListItemViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: ListItemViewHolder?, position: Int) {
        val listItem: ListItem = itemList[position]
        holder!!.bind(listItem)
    }

    fun setItems(list: List<ListItem>){
        itemList = list
        notifyDataSetChanged()
    }

    interface Listener{
        fun onEdit(listItem: ListItem)
        fun onSelect(listItem: ListItem)
        fun onDelete(listItem: ListItem)
    }

    class EventHandler(val listener: Listener){
        fun onEdit(listItem: ListItem) = listener.onEdit(listItem)
        fun onSelect(listItem: ListItem) = listener.onSelect(listItem)
        fun onDelete(listItem: ListItem) = listener.onDelete(listItem)
    }

}