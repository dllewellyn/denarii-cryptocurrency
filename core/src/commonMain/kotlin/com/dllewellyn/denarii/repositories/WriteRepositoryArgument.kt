package com.dllewellyn.denarii.repositories

interface WriteRepositoryArgument<K, T> {
    suspend fun write(value : T, arg : K)
}