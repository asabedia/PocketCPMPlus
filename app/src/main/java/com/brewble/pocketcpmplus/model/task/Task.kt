package com.brewble.pocketcpmplus.model.task

import com.brewble.pocketcpmplus.model.ListItem
import java.util.Date

class Task(override val name: String, val startDate: Date, val endateDate: Date): ListItem()