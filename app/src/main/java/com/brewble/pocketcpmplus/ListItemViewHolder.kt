package com.brewble.pocketcpmplus

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.PopupMenu
import com.brewble.pocketcpmplus.databinding.ListItemLayoutBinding
import com.brewble.pocketcpmplus.model.ListItem

/**
 * Created by ashkanabedian on 2018-02-11.
 */
class ListItemViewHolder(private val binding: ListItemLayoutBinding, context: Context, val listener: Listener) : RecyclerView.ViewHolder(binding.root), View.OnClickListener
{
    private val popupMenu: PopupMenu = PopupMenu(context, binding.root.findViewById(R.id.menu))
    private val eventHandler = EventHandler(popupMenu)

    init {
        popupMenu.inflate(R.menu.list_item_menu)
        popupMenu.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.edit -> {
                    listener.onEdit(adapterPosition)
                    true
                }
                R.id.delete -> {
                    listener.onDelete(adapterPosition)
                    true
                }
                else -> false
            }
        }
    }

    fun bind(listItem: ListItem) {
        binding.setVariable(BR.listItem, listItem)
        binding.eventHanlder = eventHandler
        binding.cardView.setOnClickListener(this)
        binding.executePendingBindings()
    }

    interface Listener{
        fun onEdit(selectedPosition: Int)
        fun onDelete(selectedPosition: Int)
        fun onSelect(selectedPosition: Int)
    }

    override fun onClick(v: View?) {
        listener.onSelect(adapterPosition)
    }

    class EventHandler(private val popupMenu: PopupMenu) {
        fun onMenu() {
            popupMenu.show()
        }
    }
}