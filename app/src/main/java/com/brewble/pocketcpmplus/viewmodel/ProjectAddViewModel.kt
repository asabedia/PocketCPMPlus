package com.brewble.pocketcpmplus.viewmodel

import android.arch.lifecycle.*
import android.support.v4.app.Fragment
import com.brewble.pocketcpmplus.model.project.Project
import com.brewble.pocketcpmplus.model.Result

class ProjectAddViewModel: ViewModel() {
    val projectRequest: MutableLiveData<Project> = MutableLiveData()

    val projectResult = Transformations.map(projectRequest) {
        if (it.isValid()) {
            return@map Result.success(it)
        } else {
            return@map Result.noOp<Project>()
        }
    }!!

    companion object {
        fun create(fragment: Fragment): ProjectAddViewModel{
            return ViewModelProviders.of(fragment).get(ProjectAddViewModel::class.java)
        }
    }


}
