package com.brewble.pocketcpmplus.model.project

import com.brewble.pocketcpmplus.model.ListItem
import com.brewble.pocketcpmplus.model.task.Task
import java.util.*

data class Project(val startDate: String, override val name: String, override val id: UUID = UUID.randomUUID()): ListItem(){
    lateinit var taskList: ArrayList<Task>
    fun isValid(): Boolean {
        return !name.isEmpty()
    }
    fun getTOC(): Int{
        var max= 0
        for(task in taskList){
            if(max<task.getEF()) {
                max = task.getEF()
            }
        }
        return max
    }
}