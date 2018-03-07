package com.brewble.pocketcpmplus.model

/**
 * Created by ashkanabedian on 2018-02-18.
 */
interface ResultListener<T> {
    fun onResult(result: Result<T>)
}