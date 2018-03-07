package com.brewble.pocketcpmplus.model

/**
 * Created by ashkanabedian on 2018-02-18.
 */
class Result<T> private constructor(val status: Status, val payload: T){
    enum class Status{
        SUCCESS, NO_OP
    }

    companion object {
        fun <T> success(payload: T): Result<T>{
            return Result(Status.SUCCESS, payload)
        }
        fun <T> noOp(): Result<T?>{
            return Result(Status.NO_OP, null)
        }
    }
}