package com.dllewellyn.coinbaseapi

interface WriteRepository<T> {
    suspend fun write(value : T)
}