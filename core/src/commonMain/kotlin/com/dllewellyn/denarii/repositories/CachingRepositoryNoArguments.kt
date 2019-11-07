package com.dllewellyn.denarii.repositories

class CachingRepositoryNoArguments<T>(
    private val remote : ReadOnlyRepositoryNoArguments<List<T>>,
    private val local : ReadOnlyRepositoryNoArguments<List<T>>,
    private val localWrite : WriteRepositorySingleArgument<List<T>>
): ReadOnlyRepositoryNoArguments<List<T>> {

    private lateinit var cache : List<T>

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