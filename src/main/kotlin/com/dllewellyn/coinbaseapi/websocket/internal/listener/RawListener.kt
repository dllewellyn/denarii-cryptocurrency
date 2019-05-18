package com.dllewellyn.coinbaseapi.websocket.internal.listener

import com.dllewellyn.coinbaseapi.models.EventResponse
import com.dllewellyn.coinbaseapi.models.only
import com.dllewellyn.coinbaseapi.websocket.internal.models.SnapshotResponseApi
import com.dllewellyn.coinbaseapi.websocket.internal.models.UpdateResponseApi
import com.google.gson.Gson
import com.google.gson.JsonObject
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import okio.ByteString
import java.lang.IllegalArgumentException

class RawListener : WebSocketListener() {

    val event : Subject<EventResponse> = PublishSubject.create()
    private val gson = Gson()

    override fun onMessage(webSocket: okhttp3.WebSocket, text: String) {
        super.onMessage(webSocket, text)
        text.let(::toJson)
            .let(::toEventResponse)
            .let { it.forEach(event::onNext) }
    }

    override fun onMessage(webSocket: WebSocket, bytes: ByteString) {
        super.onMessage(webSocket, bytes)
        println(bytes)
    }

    override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
        super.onFailure(webSocket, t, response)
        event.onError(t)
    }

    private fun toJson(text : String) = gson.fromJson(text, JsonObject::class.java)

    private fun toEventResponse(jsonObject: JsonObject) : List<EventResponse> {
        return when (jsonObject["type"].asString) {
            "snapshot" -> gson.fromJson(jsonObject, SnapshotResponseApi::class.java).toCore().only()
            "l2update" -> gson.fromJson(jsonObject, UpdateResponseApi::class.java).toCore()
            else -> listOf()
        }
    }
}