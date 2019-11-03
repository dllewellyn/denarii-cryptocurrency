package com.dllewellyn.denarii.callbacks

interface GenericCallback<T> {
    fun onSuccess(value: T)
    fun onError(exception: Throwable)
}

class SimpleCallback<T>(private val success: (T) -> Unit, private val failure: (Throwable) -> Unit) :
    GenericCallback<T> {
    override fun onSuccess(value: T) = success(value)

    override fun onError(exception: Throwable) = failure(exception)

}
