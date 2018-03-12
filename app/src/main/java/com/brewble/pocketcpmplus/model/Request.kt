package com.brewble.pocketcpmplus.model

import com.brewble.pocketcpmplus.model.event.EventType

/**
 * Created by ashkanabedian on 2018-03-06.
 */
data class Request<out I>(val requestType: EventType, val payload: I)