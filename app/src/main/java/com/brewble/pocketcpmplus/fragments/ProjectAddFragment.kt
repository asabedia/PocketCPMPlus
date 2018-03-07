package com.brewble.pocketcpmplus.fragments

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.LifecycleRegistry
import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.brewble.pocketcpmplus.model.project.ProjectAddEvent
import com.brewble.pocketcpmplus.model.Result
import com.brewble.pocketcpmplus.model.project.Project
import com.brewble.pocketcpmplus.view.ProjectAddView
import com.brewble.pocketcpmplus.viewmodel.ProjectAddViewModel
import org.greenrobot.eventbus.EventBus

class ProjectAddFragment: Fragment(), LifecycleOwner {

    private lateinit var lifecycleRegistry: LifecycleRegistry
    private lateinit var view: ProjectAddView

    private val observer = Observer<Result<Project?>>{
        Log.d(this.javaClass.name, "in observer")
        when(it?.status){
            Result.Status.SUCCESS -> {
                EventBus.getDefault().post(ProjectAddEvent(it.payload!!))
            }
        }
    }

    private lateinit var viewModel: ProjectAddViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        lifecycleRegistry = LifecycleRegistry(this)
        super.onCreate(savedInstanceState)

        view = ProjectAddView(layoutInflater, object: ProjectAddView.Listener{
            override fun onOk(project: Project) {
                viewModel.projectRequest.postValue(project)
            }

            override fun onCancel() {

            }
        })
    }

    override fun onResume() {
        super.onResume()
        lifecycleRegistry.markState(Lifecycle.State.RESUMED)
    }

    override fun onStart() {
        super.onStart()
        lifecycleRegistry.markState(Lifecycle.State.STARTED)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return view.rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        lifecycleRegistry.markState(Lifecycle.State.CREATED)
        viewModel = ProjectAddViewModel.create(this)
        viewModel.projectResult.removeObserver(observer)
        viewModel.projectResult.observe(this, observer)
    }

    override fun getLifecycle(): Lifecycle {
        return lifecycleRegistry
    }
}