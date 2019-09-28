package com.dllewellyn.coinbaseapi.repositories

open class SynchronisedDatabase<T>(
    private val remoteRepository: ReadOnlyRepository<T>,
    private val localRepository: WriteRepository<T>,
    private val localReadOnlyRepository: ReadOnlyRepository<T?>
) : ReadOnlyRepository<T> {

    override suspend fun retrieveData(): T = localReadOnlyRepository.retrieveData()?.let {
        it
    } ?: refresh()

    suspend fun refresh(): T = with(remoteRepository.retrieveData()) {
        localRepository.write(this)
        this
    }
}