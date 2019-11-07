package com.dllewellyn.denarii.retrievers

import com.dllewellyn.denarii.repositories.ReadOnlyRepositoryNoArguments

class CompositeRetriever<T> : ReadOnlyRepositoryNoArguments<List<T>> {
    val retrievers = mutableListOf<ReadOnlyRepositoryNoArguments<List<T>>>()

    override suspend fun retrieveData(): List<T> {
        val result = mutableListOf<T>()

        retrievers.forEach {
            result.addAll(it.retrieveData())
        }

        return result
    }
}