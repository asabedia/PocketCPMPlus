package com.brewble.pocketcpmplus.model

import com.brewble.pocketcpmplus.model.project.Project

/**
 * Created by ashkanabedian on 2018-02-18.
 */
class Result<O> private constructor(val status: Status, val payload: O){
    enum class Status{
        SUCCESS, NO_OP
    }

    companion object {
        fun <O> success(payload: O): Result<O>{
            return Result(Status.SUCCESS, payload)
        }
        fun <O> noOp(): Result<O?>{
            return Result(Status.NO_OP, null)
        }
    }
}