package com.dllewellyn.coinbaseapi.retrievers

import com.dllewellyn.coinbaseapi.repositories.ReadOnlyRepository
import com.dllewellyn.coinbaseapi.repositories.WriteRepository

class CachingRepository<T>(
    private val remote : ReadOnlyRepository<List<T>>,
    private val local : ReadOnlyRepository<List<T>>,
    private val localWrite : WriteRepository<List<T>>
): ReadOnlyRepository<List<T>> {

    lateinit var cache : List<T>

    suspend fun initialise() {
        cache = local.retrieveData()
    }

    suspend fun refresh() = with (remote.retrieveData()) {
        localWrite.write(this)
        cache = this
    }

    override suspend fun retrieveData() = cache
}