package com.brewble.pocketcpmplus.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import android.support.v4.app.Fragment
import com.brewble.pocketcpmplus.model.ListItem
import com.brewble.pocketcpmplus.model.Request
import com.brewble.pocketcpmplus.model.event.Event
import com.brewble.pocketcpmplus.model.event.EventType
import org.greenrobot.eventbus.EventBus

/**
 * Created by ashkanabedian on 2018-03-06.
 */
class ListItemViewModel: ViewModel() {

    init {
        EventBus.getDefault().register(this)
    }

    val listItemRequest = MutableLiveData<Request<ListItem>>()

    val listItemApproved = Transformations.map(listItemRequest){
        when(it.requestType){
            EventType.Delete -> {
                EventBus.getDefault().post(Event.Delete(it))
            }
        }
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