package com.dllewellyn.denarii.repositories

interface WriteRepositorySingleArgument<T> {
    suspend fun write(value : T)
}