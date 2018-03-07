package com.brewble.pocketcpmplus.model

/**
 * Created by ashkanabedian on 2018-03-06.
 */
data class Request<out I>(val requestType: RequestType, val payload: I)