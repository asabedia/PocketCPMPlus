package com.brewble.pocketcpmplus

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.brewble.pocketcpmplus.databinding.ListItemLayoutBinding
import com.brewble.pocketcpmplus.model.ListItem
import com.brewble.pocketcpmplus.view.ListView


class ListAdapter(val listener: ListView.Listener) : RecyclerView.Adapter<ListItemViewHolder>(){

    private var itemList: List<ListItem>

    init {
        itemList = ArrayList()
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ListItemViewHolder {
        val context = parent!!.context
        val layoutInflater = LayoutInflater.from(context)
        val itemView: ListItemLayoutBinding = DataBindingUtil.inflate(layoutInflater, R.layout.list_item_layout, parent, false)
        return ListItemViewHolder(itemView, context, object: ListItemViewHolder.Listener{
            override fun onDelete(selectedPosition: Int) {
                listener.onDelete(itemList[selectedPosition])
            }
            override fun onSelect(selectedPosition: Int) {
                listener.onSelect(itemList[selectedPosition])
            }
            override fun onEdit(selectedPosition: Int) {
                listener.onEdit(itemList[selectedPosition])
            }
        })
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: ListItemViewHolder?, position: Int) {
        val listItem: ListItem = itemList[position]
        holder!!.bind(listItem)
    }

    fun setItems(list: List<ListItem>) {
        itemList = list
        notifyDataSetChanged()
    }
}