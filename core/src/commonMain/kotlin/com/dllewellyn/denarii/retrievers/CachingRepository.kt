package com.dllewellyn.denarii.retrievers

import com.dllewellyn.coinbaseapi.repositories.ReadOnlyRepository
import com.dllewellyn.coinbaseapi.repositories.WriteRepository

class CachingRepository<T>(
    private val remote : ReadOnlyRepository<List<T>>,
    private val local : ReadOnlyRepository<List<T>>,
    private val localWrite : WriteRepository<List<T>>
): ReadOnlyRepository<List<T>> {

    lateinit var cache : List<T>

    suspend fun initialise() {
        cache = try {
            local.retrieveData()
        } catch (exception : Exception) {
            listOf()
        }
    }

    suspend fun refresh() = with (remote.retrieveData()) {
        localWrite.write(this)
        cache = this
    }

    override suspend fun retrieveData() = cache
}