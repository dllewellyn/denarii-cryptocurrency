package com.dllewellyn.coinbaseapi.repositories

interface ReadOnlyRepository<T> {
    suspend fun retrieveData() : T
}