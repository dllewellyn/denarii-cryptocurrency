package com.dllewellyn.denarii.repositories

interface ReadOnlyRepositoryArgument<K,T> {
    suspend fun retrieveData(arg : K) : T
}