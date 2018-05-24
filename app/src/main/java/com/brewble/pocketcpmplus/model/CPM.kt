package com.brewble.pocketcpmplus.model

import com.brewble.pocketcpmplus.model.task.Task

class CPM(task:Task) {
    var ES: Int=0
    var LS: Int=0
    var LF: Int=0
    var EF: Int=0
    var task: Task = task

    fun getLateFinish(task:Task):Int{
        if (task.getDepends().isEmpty()) {// if no successors then the late finish is the time of completion of project
            LF = task.project.getTOC()
        }
        else  {// if it does have successor then the late finish is the smallest late start of its successors
            var min = Double.POSITIVE_INFINITY.toInt()
            for (i in task.getDepends()) {
                if (getLateStart(i) < min) {
                    min = getLateStart(i)
                }
            }
            LF = min
        }
        return LF
    }

    fun getEarlyFinish(task:Task):Int{
        EF= getEarlyStart(task)+task.getDuration() //early finish is early start plus duration
        return EF
    }

    fun getLateStart(task: Task):Int{
        LS= getLateFinish(task)-task.getDuration() //late start is late finish minus duration
        return LS
    }

    fun getEarlyStart(task: Task):Int{
        if (task.getPreds().isEmpty()) { ///if it has no preds then Early start is 0
            ES = 0
        } else  { // if it does have preds then get the max early finish of its preds and thats the early start of this one
            var max: Int = 0
            for (i in task.getPreds()) {
                if (getEarlyFinish(i) > max) {
                    max = getEarlyFinish(i)
                }
            }
            ES = max
        }
        return ES
    }
}