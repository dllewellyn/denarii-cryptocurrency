package com.dllewellyn.coinbaseapi.interfaces

import com.dllewellyn.coinbaseapi.models.Channel
import com.dllewellyn.coinbaseapi.models.CurrencyPair
import com.dllewellyn.coinbaseapi.models.EventResponse
import io.reactivex.Observable

interface EventSubscription {

    fun subscribeToEvent(channel: List<Channel>, vararg pair : CurrencyPair) : Observable<EventResponse>
    fun unsubscribeToEvent(channel: List<Channel>, vararg pair : CurrencyPair)
}