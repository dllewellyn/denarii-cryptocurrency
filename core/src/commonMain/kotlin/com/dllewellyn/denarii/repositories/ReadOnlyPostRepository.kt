package com.dllewellyn.coinbaseapi.repositories

interface ReadOnlyPostRepository<Arg, T> {
    suspend fun retrieveData(arg : Arg) : T
}