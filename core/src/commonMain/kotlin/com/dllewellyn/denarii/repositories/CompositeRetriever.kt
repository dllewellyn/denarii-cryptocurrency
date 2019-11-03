package com.dllewellyn.coinbaseapi.retrievers

import com.dllewellyn.coinbaseapi.repositories.ReadOnlyRepository

class CompositeRetriever<T> : ReadOnlyRepository<List<T>> {
    val retrievers = mutableListOf<ReadOnlyRepository<List<T>>>()

    override suspend fun retrieveData(): List<T> {
        val result = mutableListOf<T>()

        retrievers.forEach {
            result.addAll(it.retrieveData())
        }

        return result
    }
}