package com.brewble.pocketcpmplus.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import android.support.v4.app.Fragment
import com.brewble.pocketcpmplus.model.ListItem
import com.brewble.pocketcpmplus.model.Request

/**
 * Created by ashkanabedian on 2018-03-06.
 */
class ListItemViewModel: ViewModel() {

    val listItemRequest = MutableLiveData<Request<ListItem>>()

    val listItemApproved = Transformations.map(listItemRequest){
        //if delete, make repo call to remove form data base (EventBus)
        //if select, launch project display
        //if edit launch, add fragment with pre-filled fields
    }

    companion object {
        fun create(fragment: Fragment): ListItemViewModel{
            return ViewModelProviders.of(fragment).get(ListItemViewModel::class.java)
        }
    }
}