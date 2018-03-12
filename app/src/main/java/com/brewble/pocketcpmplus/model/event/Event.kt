package com.brewble.pocketcpmplus.model.event

/**
 * Created by ashkanabedian on 2018-03-03.
 */
class Event<out T> private constructor(val payload: T, val eventType: EventType) {
    companion object {
        fun <T> Delete (payload: T): Event<T>{
            return Event(payload, EventType.Delete)
        }
        fun <T> Select (payload: T): Event<T>{
            return Event(payload, EventType.Select)
        }
        fun <T> Edit (payload: T): Event<T>{
            return Event(payload, EventType.Edit)
        }
        fun <T> Add (payload: T): Event<T>{
            return Event(payload, EventType.Add)
        }
    }
}