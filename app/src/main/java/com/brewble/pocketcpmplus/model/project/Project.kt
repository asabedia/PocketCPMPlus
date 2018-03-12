package com.brewble.pocketcpmplus.model.project

import com.brewble.pocketcpmplus.model.ListItem
import com.brewble.pocketcpmplus.model.task.Task
import java.time.LocalDate
import java.util.*

data class Project(val startDate: String, override val name: String, override val id: UUID = UUID.randomUUID()): ListItem(){
    fun isValid(): Boolean {
        return !name.isEmpty()
    }
}