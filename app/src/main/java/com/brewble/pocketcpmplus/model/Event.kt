package com.brewble.pocketcpmplus.model

/**
 * Created by ashkanabedian on 2018-03-03.
 */
interface Event<T> {
    fun getResult(): T
}