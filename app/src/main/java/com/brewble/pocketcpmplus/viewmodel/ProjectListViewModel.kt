package com.brewble.pocketcpmplus.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import android.support.v4.app.Fragment
import com.brewble.pocketcpmplus.model.project.Project

class ProjectListViewModel: ViewModel(){

    val projectListRequest: MutableLiveData<List<Project>> = MutableLiveData()

    val projectListApproved = Transformations.map(projectListRequest){
        return@map it
    }

    companion object {
        fun create(fragment: Fragment): ProjectListViewModel{
            return ViewModelProviders.of(fragment).get(ProjectListViewModel::class.java)
        }
    }
}
