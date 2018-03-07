package com.brewble.pocketcpmplus.fragments

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.LifecycleRegistry
import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.brewble.pocketcpmplus.R
import com.brewble.pocketcpmplus.model.ListItem
import com.brewble.pocketcpmplus.model.project.ProjectAddEvent
import com.brewble.pocketcpmplus.model.project.Project
import com.brewble.pocketcpmplus.view.ListView
import com.brewble.pocketcpmplus.viewmodel.ProjectListViewModel
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode


class ProjectListFragment: Fragment(), LifecycleOwner {

    private lateinit var lifecycleRegistry: LifecycleRegistry
    private lateinit var view: ListView

    private lateinit var viewModel: ProjectListViewModel

    private val observer = Observer<List<Project>>{
        Log.d(this.javaClass.name, "new project added")
        if(it!!.isNotEmpty()){
            view.updateList(it)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        lifecycleRegistry = LifecycleRegistry(this)
        super.onCreate(savedInstanceState)

        EventBus.getDefault().register(this)

        viewModel = ProjectListViewModel.create(this)

        view = ListView(layoutInflater, LinearLayoutManager(context), object : ListView.Listener {
            override fun onDelete(listItem: ListItem) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onEdit(listItem: ListItem) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onSelect(listItem: ListItem) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onAdd() {
                launchAddProjFragment()
            }
        })
    }

    private fun launchAddProjFragment() {
        Log.d(this.javaClass.name, "add project Selected")
        if(lifecycleRegistry.currentState.isAtLeast(Lifecycle.State.STARTED)){
            activity
                    .supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.container, ProjectAddFragment())
                    .addToBackStack("ProjectAddFragment")
                    .commit()
        }
    }

    override fun onStart() {
        super.onStart()
        lifecycleRegistry.markState(Lifecycle.State.STARTED)
    }

    override fun onResume() {
        super.onResume()
        lifecycleRegistry.markState(Lifecycle.State.RESUMED)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        lifecycleRegistry.markState(Lifecycle.State.CREATED)
        viewModel.projectListApproved.removeObserver(observer)
        viewModel.projectListApproved.observe(this, observer)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return view.rootView
    }

    override fun getLifecycle(): Lifecycle {
        return lifecycleRegistry
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    fun onMessageEvent(project: ProjectAddEvent) {
        Log.d(this.javaClass.name, "projectEvent received")
        if(viewModel.projectListApproved.value != null){
            val list = ArrayList<Project>(viewModel.projectListApproved.value)
            list.add(project.getResult())
            viewModel.projectListRequest.postValue(list)
        }else{
            val list =  ArrayList<Project>()
            list.add(project.getResult())
            viewModel.projectListRequest.postValue(list)
        }
    }
}
