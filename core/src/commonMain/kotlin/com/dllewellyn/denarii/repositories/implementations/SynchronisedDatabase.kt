package com.dllewellyn.denarii.repositories

open class SynchronisedDatabase<T>(
    private val remoteRepository: ReadOnlyRepositoryNoArguments<T>,
    private val localRepository: WriteRepositorySingleArgument<T>,
    private val localReadOnlyRepository: ReadOnlyRepositoryNoArguments<T?>
) : ReadOnlyRepositoryNoArguments<T> {

    override suspend fun retrieveData(): T = localReadOnlyRepository.retrieveData()?.let {
        it
    } ?: refresh()

    suspend fun refresh(): T = with(remoteRepository.retrieveData()) {
        localRepository.write(this)
        this
    }
}