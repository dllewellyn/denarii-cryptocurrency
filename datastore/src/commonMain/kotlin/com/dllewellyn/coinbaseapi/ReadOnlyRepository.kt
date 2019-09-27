package com.dllewellyn.coinbaseapi

interface ReadOnlyRepository<T> {
    suspend fun retrieveData() : T
}