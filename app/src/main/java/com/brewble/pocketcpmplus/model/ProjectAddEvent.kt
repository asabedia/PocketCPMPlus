package com.brewble.pocketcpmplus.model

import com.brewble.pocketcpmplus.model.project.Project

/**
 * Created by ashkanabedian on 2018-03-03.
 */
data class ProjectAddEvent(private val result: Project): Event<Project> {
    override fun getResult(): Project {
        return result
    }

}