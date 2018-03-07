package com.brewble.pocketcpmplus.model.project

import com.brewble.pocketcpmplus.model.ListItem
import com.brewble.pocketcpmplus.model.task.Task
import java.time.LocalDate
import java.util.Date
import  java.util.LinkedList

class Project(val startDate: String, override val name: String): ListItem(){
    lateinit var taskList: LinkedList<Task>
    fun isValid(): Boolean {
        return !name.isEmpty()
    }
}