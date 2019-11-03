package com.dllewellyn.denarii.callbacks


interface Retriever<T> {
    fun registerForReceiver(receiver: GenericCallback<T>)
    fun update(value: T)
    fun error(throwable: Throwable)
    fun unregister(receiver: GenericCallback<T>)
}

open class BaseRetriever<T> : Retriever<T> {

    private val callbacks = mutableListOf<GenericCallback<T>>()

    override fun registerForReceiver(receiver: GenericCallback<T>) {
        callbacks.add(receiver)
    }

    override fun update(value: T) {
        callbacks.forEach {
            it.onSuccess(value)
        }
    }

    override fun error(throwable: Throwable) {
        callbacks.forEach {
            it.onError(throwable)
        }
    }

    override fun unregister(receiver: GenericCallback<T>) {
        callbacks.remove(receiver)
    }

}
