package com.dllewellyn.coinbaseapi.repositories

interface ReadOnlyRepositoryArgument<K,T> {
    suspend fun retrieveData(arg : K) : T
}