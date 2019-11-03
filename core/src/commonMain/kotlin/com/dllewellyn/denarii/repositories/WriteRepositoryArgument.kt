package com.dllewellyn.coinbaseapi.repositories

interface WriteRepositoryArgument<K, T> {
    suspend fun write(value : T, arg : K)
}