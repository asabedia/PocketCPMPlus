package com.brewble.pocketcpmplus.view.fragments

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.brewble.pocketcpmplus.model.Result
import com.brewble.pocketcpmplus.model.event.Event
import com.brewble.pocketcpmplus.model.project.Project
import com.brewble.pocketcpmplus.view.ProjectAddView
import com.brewble.pocketcpmplus.viewmodel.ProjectAddViewModel
import org.greenrobot.eventbus.EventBus
import java.util.*

class ProjectAddFragment: Fragment() {

    private lateinit var view: ProjectAddView
    private lateinit var viewModel: ProjectAddViewModel

    private val observer = Observer<Result<Project?>>{
        Log.d(this.javaClass.name, "in observer")
        when(it?.status){
            Result.Status.SUCCESS -> {
                EventBus.getDefault().post(Event.Add(it.payload!!))
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        view = ProjectAddView(layoutInflater, object: ProjectAddView.Listener{
            override fun onOk(project: Project) {
                viewModel.projectAddRequest.postValue(project)
            }
            override fun onCancel() {

            }
        })
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return view.rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ProjectAddViewModel.create(this)
        viewModel.projectResult.removeObserver(observer)
        viewModel.projectResult.observe(this, observer)
    }

}