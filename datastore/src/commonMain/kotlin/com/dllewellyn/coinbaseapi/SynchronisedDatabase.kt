package com.dllewellyn.coinbaseapi

open class SynchronisedDatabase<T>(
    private val remoteRepository: ReadOnlyRepository<T>,
    private val localRepository: WriteRepository<T>,
    private val localReadOnlyRepository: ReadOnlyRepository<T?>
) {

    suspend fun refresh(): T = with(remoteRepository.retrieveData()) {
        localRepository.write(this)
        this
    }

    suspend fun retrieve(): T = localReadOnlyRepository.retrieveData()?.let {
        it
    } ?: refresh()
}