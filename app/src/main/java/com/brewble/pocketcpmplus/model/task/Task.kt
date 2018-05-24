package com.brewble.pocketcpmplus.model.task

import com.brewble.pocketcpmplus.model.CPM
import com.brewble.pocketcpmplus.model.ListItem
import com.brewble.pocketcpmplus.model.project.Project
import java.util.*

data class Task(override val name: String, override val id: UUID = UUID.randomUUID(), val startDate: String, val endDate: Date): ListItem(){
    private var ES: Int=0
    private var EF: Int=0
    private var LS: Int=0
    private var LF: Int=0
    val mCPM= CPM()
    private var duration: Int=0
    lateinit var  project: Project
    private var depends= ArrayList<Task>()
    private var preds= ArrayList<Task>()
    fun getDepends():ArrayList<Task>{
        return depends
    }
    fun getPreds():ArrayList<Task>{
        return preds
    }
    fun getDuration() :Int{
        return duration
    }
    fun getES() :Int{
        ES= mCPM.getEarlyStart(this)
        return ES
    }
    fun getEF() :Int{
        EF= mCPM.getEarlyFinish(this)
        return EF
    }
    fun getLS() :Int{
        LS= mCPM.getLateStart(this)
        return LS
    }
    fun getLF() :Int{
        LF= mCPM.getLateFinish(this)
        return LF
    }
}