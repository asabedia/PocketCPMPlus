package com.brewble.pocketcpmplus.model.task

import com.brewble.pocketcpmplus.model.ListItem
import java.util.*

data class Task(override val name: String, override val id: UUID = UUID.randomUUID(), val startDate: String, val endDate: Date): ListItem()