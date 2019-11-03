package com.dllewellyn.coinbaseapi.repositories

interface WriteRepository<T> {
    suspend fun write(value : T)
}