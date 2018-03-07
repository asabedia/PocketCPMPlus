package com.brewble.pocketcpmplus

import android.support.v7.widget.RecyclerView
import com.brewble.pocketcpmplus.databinding.ListItemLayoutBinding
import com.brewble.pocketcpmplus.model.ListItem

/**
 * Created by ashkanabedian on 2018-02-11.
 */
class ListItemViewHolder(val binding: ListItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(listItem: ListItem) {
        binding.setVariable(BR.listItem, listItem)
        binding.executePendingBindings()
    }
}