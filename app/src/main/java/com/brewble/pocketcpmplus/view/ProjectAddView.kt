package com.brewble.pocketcpmplus.view

import android.databinding.BaseObservable
import android.databinding.Bindable
import android.databinding.DataBindingUtil.inflate
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import com.brewble.pocketcpmplus.BR
import com.brewble.pocketcpmplus.R
import com.brewble.pocketcpmplus.databinding.ProjectAddLayoutBinding
import com.brewble.pocketcpmplus.model.project.Project

/**
 * Created by ashkanabedian on 2018-03-03.
 */
class ProjectAddView(inflater: LayoutInflater, listener: Listener) {

    var rootView: View? = null
    var view: ProjectAddLayoutBinding
    var model: Model
    private val eventHandler: EventHandler

    init {
        view  = inflate(inflater, R.layout.project_add_layout, null, false)
        rootView = view.root
        model = Model()
        view.model = model
        eventHandler = EventHandler(listener, model)
        view.eventHandler = eventHandler
    }

    class Model: BaseObservable(){
        @Bindable var name: String = ""
            set(value) {
                field = value
                notifyPropertyChanged(BR.name)
            }

        @Bindable var startDate: String = ""
            set(value) {
                field = value
                notifyPropertyChanged(BR.startDate)
            }
    }

    interface Listener{
        fun onOk(project: Project)
        fun onCancel()
    }

    class EventHandler(private val listener: Listener, private var model: Model){
        fun onOk(){
            Log.d("${this.javaClass.name}", "Project Add Selected")
            listener.onOk(Project(model.startDate, model.name))
        }
        fun onCancel(){
            listener.onCancel()
        }
    }
}