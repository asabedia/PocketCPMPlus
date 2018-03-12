package com.brewble.pocketcpmplus.view

import android.databinding.DataBindingUtil.inflate
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import com.brewble.pocketcpmplus.ListAdapter
import com.brewble.pocketcpmplus.R
import com.brewble.pocketcpmplus.databinding.ListLayoutBinding
import com.brewble.pocketcpmplus.model.ListItem

/**
 * Created by ashkanabedian on 2018-02-10.
 */
class ListView(inflater: LayoutInflater, layoutManager: LinearLayoutManager, listener: Listener) {

    var rootView: View? = null
    var recyclerView: RecyclerView
    var viewState: Bundle? = null
    private var eventHandler: EventHandler
    private val adapter: ListAdapter
    private var view: ListLayoutBinding

    init {
        view = inflate(inflater, R.layout.list_layout, null, false)
        eventHandler = EventHandler(listener)
        view.eventHandler = eventHandler
        adapter = ListAdapter(listener)
        rootView = view.root
        recyclerView = view.recyclerView
        recyclerView.adapter = adapter
        recyclerView.layoutManager = layoutManager
    }

    fun updateList(list: List<ListItem>){
        adapter.setItems(list)
    }

    interface Listener{
        fun onAdd()
        fun onEdit(listItem: ListItem)
        fun onDelete(listItem: ListItem)
        fun onSelect(listItem: ListItem)
    }

    class EventHandler(val listener: Listener){
        fun onAdd(){
            listener.onAdd()
        }
    }
}